# PGMM 叠加层/绘制系统说明（Minimap / Full Map / World / OverlayUI）

本文档描述 PGMM 的绘制入口、坐标系、以及与其他模组（MI2、MindustryX OverlayUI）的集成方式。

---

## 1) 绘制入口与坐标系

- HUD 小地图叠加（UI 内嵌）
  - 通过 `ensureOverlayAttached()` 把自定义 `MinimapOverlay` 作为 minimap 的 child 挂上去。
- 全屏地图叠加（按 M 打开）
  - 通过 `Trigger.uiDrawEnd`，拿到全屏地图的 pan/zoom/baseSize（`FullMinimapAccess` 反射），构造 transform 再绘制 overlay/marker。
- 主游戏画面世界坐标叠加
  - 通过 `Trigger.draw` 执行 `drawWorldRescueOverlay()`：在世界坐标系画救援多边形与冲击反应堆标记。

---

## 2) Power Table（电力表）显示模式

Power Table 可在两种宿主中显示：

1) Vanilla / 未安装 MindustryX：
   - 挂到 `ui.hudGroup`，并在 `act()` 中锚定到 minimap 附近（左侧）。
2) 安装了 MindustryX：
   - 通过反射调用 `OverlayUI.INSTANCE.registerWindow(...)` 注册为 OverlayUI 窗口。
   - 此时 `PowerTableOverlay` 会进入 `hostedByOverlayUI=true` 模式：不再自己 setPosition，避免与 OverlayUI 拖拽/吸附冲突。

### Power Chart（发电折线图）

- 与 Power Table 相同的宿主策略：MindustryX 下注册为 `pgmm-power-chart` OverlayUI 窗口；否则挂在 `ui.hudGroup` 中小地图左侧（空间不足时放到小地图下方）。
- 数据采集在 `Trigger.update` 中按 1 秒采样（`PowerChartData`）：遍历玩家队伍各电网的 `producers`，按发电机 `block.id` 聚合 `getPowerProduction() * 60 * timeScale()`（`getPowerProduction()` 已包含发电机效率）；同时记录每个采样点耗电最大的 `consumers` 建筑名称与坐标。
- 绘制在 `PowerChartCanvas`（自定义 `Element`）：折线模式按窗口内最大发电量缩放；占比模式按每个采样点的 `value / total * 100` 绘制。X 轴使用真实采样时间戳，暂停/关闭期间不会把时间轴压缩。鼠标悬停用 `screenToLocalCoordinates` 定位最近采样点，绘制十字线与详情浮层。
- 历史窗口默认 120 秒，可在设置中调整；世界加载、切换队伍或软重启时清空。

---

## 3) MI2 小地图集成（可选）

若玩家安装 MI2-Utilities：

- 通过遍历 `Vars.mods.list()`，在各自 `mod.loader` 中尝试 `Class.forName("mi2u.ui.MinimapMindow", false, mod.loader)`。
- 成功后反射拿到 MI2 minimap 的 element 与 rect/setRect 接口，把 overlay 作为 minimap 的 sibling 挂在其 parent 下。

失败策略：反射失败/MI2 尚未初始化时静默跳过，后续定时重试。

---

## 4) MindustryX OverlayUI 集成（可选）

文件内实现：`PowerGridMinimapMod.MindustryXOverlayUI`

- 检测：`Vars.mods.locateMod("mindustryx") != null`
- 反射：
  - `Class.forName("mindustryX.features.ui.OverlayUI")`
  - `OverlayUI.INSTANCE` + `registerWindow(String, Table)`
  - 可选调用 `window.setAvailability(Prov<Boolean>)`
- 同步开关：
  - OverlayUI 窗口实际是否显示由 `window.data.enabled` 与 `pinned` 决定。
  - PGMM 把 `pgmm-power-table` 的 boolean 映射到 enabled/pinned，以保持“开关即显示”的玩家体验。

