# PGMM 主要接口说明（对外可用/可复用）

本文档列出 PGMM 中“相对稳定、对外可调用/可复用”的函数接口与入口点。PGMM 绝大多数逻辑是内部类/私有方法；因此这里以“可调用入口 + 行为契约”为主，而不是逐个 private 方法罗列。

---

## 1) 模组入口类

### `powergridminimap.PowerGridMinimapMod`

- 入口：由 `src/main/resources/mod.json` 的 `main` 指向。
- 生命周期：
  - `ClientLoadEvent`：注册 settings / 初始化颜色 / 尝试集成 MI2 / 安装控制台 API / 触发更新检查等。
  - `WorldLoadEvent`：清理缓存，延迟挂载 overlay/power table。
  - `Trigger.update`：周期性更新扫描与建议（split watcher / rescue advisor 等）。
  - `Trigger.draw`：在主游戏画面绘制救援叠加层（世界坐标系）。
  - `Trigger.uiDrawEnd`：在全屏地图上绘制 overlay 与 marker。

---

## 2) 控制台 API（F8 Console）

### `PowerGridMinimapMod.PgmmConsoleApi`

以 `pgmm` 对象挂到脚本作用域（安装成功会日志提示）。

- `String help()`
  - 返回可用命令列表（字符串）。
- `String restart()`
  - 重启 PGMM：清理缓存、重新挂载 overlay 等。
- `String rescan()`
  - 立即触发一次电网扫描/overlay 重建（绕过延迟）。
- `String mi2Refresh()`
  - 强制重新检测并重新挂载 MI2 overlay（前提：装了 MI2 且开关开启）。

注意：这些方法是给玩家/调试用的“软接口”，不是多人同步/服务器逻辑。

---

## 3) 客户端命令（聊天命令）

在 `registerClientCommands(...)` 注册（只影响客户端）：

- `/pgmm-restart`
  - 同 `restart()`：重启 PGMM。
- `/pgmm-rescan`
  - 同 `rescan()`：立即扫描重建。
- `/pgmm-mi2 [on/off/refresh]`
  - 开关或刷新 MI2 overlay。

---

## 4) 构建接口（Gradle Tasks）

在 `build.gradle` 中提供：

- `zipMod`
  - 生成 `build/libs/powergrid-minimap.zip`，并复制到 `dist/powergrid-minimap.zip`。
- `jarMod`
  - 生成 `build/libs/powergrid-minimap.jar`，并复制到 `dist/powergrid-minimap.jar`。

两者内容等价，都是 Mindustry 可加载的“类 + 资源”归档；安卓端通常更习惯使用 `.jar`。

