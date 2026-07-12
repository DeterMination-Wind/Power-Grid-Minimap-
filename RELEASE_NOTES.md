## v1.18.2 / PGMM (Power Grid Minimap)

### 中文

- 修复 MI2U 小地图在非正方形布局、缩放或边栏尺寸变化时，PGMM 叠加层使用错误绘制区域的问题；电网标记和视口范围现在会跟随实际地图区域。
- 当 MI2U 暴露的视口矩形缺失或比例异常时，PGMM 会基于当前缩放、相机和世界尺寸回退计算视口，避免标记偏移、裁剪或不显示。
- 构建 wrapper 更新到 Gradle 9.4.0。

### English

- Fixed PGMM using the wrong drawing area on MI2U minimaps with non-square layouts, zoom changes, or side-panel size changes. Grid markers and the viewport now follow the actual map area.
- Added a fallback viewport calculation based on zoom, camera position, and world size when MI2U exposes a missing or malformed view rectangle, preventing shifted, clipped, or invisible markers.
- Updated the Gradle wrapper to 9.4.0.

---

## v1.18.1 更新日志（PGMM / Power Grid Minimap）

### 🛠️ Bug 修复
- 修复：适配 MI2U 小地图的独立坐标系，避免电力叠加层在 MI2U 小地图中偏移或超出小地图范围。

## v1.14.1 更新日志（PGMM / Power Grid Minimap）

### 🛠️ Bug 修复
- 修复：关闭“救援建议”后，电力表中的 `min`（电网波动最小值）仍会持续更新并正确显示。

## v1.14.0 更新日志（PGMM / Power Grid Minimap）

### ⚠️ 断网提醒判定增强
- 新增设置项「负电持续阈值」（输入框，最大值为 `0`），用于控制分裂后子电网的负电触发线。
- 断网提醒现在要求：大电网分裂后，较小子电网需持续低于该阈值达到设定时长才会触发提醒。
- 「断网检测时间窗」改为持续判定时长，避免瞬时抖动造成误报。

## v1.13.2 更新日志（PGMM / Power Grid Minimap）

### 🛠️ Bug 修复
- 修复：在 MindustryX OverlayUI 中显示的电力表不再包含敌方电网，仅展示玩家当前队伍电网。

## v1.13.1 更新日志（PGMM / Power Grid Minimap）

### 🔧 构建依赖修复
- 构建脚本增加 Mindustry 依赖镜像仓库（Zelaux/MindustryRepo）。
- `compileOnly` 依赖切换到 `com.github.Anuken.Mindustry:core`，避免 `MindustryJitpack` 在当前环境下缺失 Arc 子模块导致的构建失败。

## v1.13.0 更新日志（PGMM / Power Grid Minimap）

### 💬 多人断电影响提醒
- 新增设置项「允许在多人游戏中发送断电提醒」。
- 新增设置项「在多人游戏中发送断电提醒的最小间隔」（1s~60s，可调）。
- 当电网断连提醒触发时，可按格式发送队伍聊天消息：`<PGMM><[red]断电建议连接点[]>(x,y)`。

## v1.12.0 更新日志（PGMM / Power Grid Minimap）

### 🗺️ 电网显示增强
- 现在会显示玩家队伍以外的队伍电网（小地图/大地图显示效果与玩家队伍一致）。
- 非玩家队伍电网仅参与可视化显示，不会触发“救援建议”与“电网断连提醒”。

### ⚠️ 救援建议默认与提示
- “救援建议”相关开关默认保持关闭。
- 在设置中增加明显提示：`制作中功能，非常不完善，不推荐开启`。
