# OverlayUI 使用说明（MindustryX）

本文档说明：在 Mindustry 模组中，如何把自己的 UI 窗口接入 MindustryX 的 `OverlayUI`（可拖拽/可固定/可缩放/可在设置里管理），并在未安装 MindustryX 时提供回退显示方案。

> 适用对象：Mindustry 模组作者（Java/Kotlin 都可）。  
> 术语：本文把你要显示的那块 UI（`Table` / `Element`）称为“窗口内容”；把 MindustryX 管理的壳（Window）称为“OverlayUI 窗口”。

---

## 1. 前置条件

1) 玩家安装了 MindustryX（模组 ID 通常为 `mindustryx`）。  
2) 你的模组运行在客户端（`ClientLoadEvent` 之后才能安全访问 UI）。  
3) 你的 UI 代码基于 Arc Scene2D（`arc.scene.*` / `arc.scene.ui.layout.Table`）。

---

## 2. OverlayUI 能提供什么

- 窗口管理：统一在 OverlayUI 面板里显示/隐藏、锁定（Pinned）、设置缩放等。
- 拖动/吸附/缩放：OverlayUI 自带窗口拖拽与约束吸附系统（玩家体验更一致）。
- 可配置持久化：OverlayUI 会把每个窗口的数据写到 `overlayUI.<name>` 的配置里（坐标、大小、锁定、缩放等）。

你只需要提供一个 `Table`（窗口内容），再调用 `registerWindow(name, table)` 注册即可。

---

## 3. 最小接入示例（Kotlin，写在 MindustryX 环境里）

如果你的项目本身依赖了 MindustryX（能直接 import 到 `mindustryX.features.ui.OverlayUI`），最简单的写法：

```kotlin
import mindustry.Vars
import mindustryX.features.ui.OverlayUI
import arc.scene.ui.layout.Table

val content = Table().apply {
    name = "my-window-content"
    // ... build UI
}

// 仅示例：你也可以放到 ClientLoadEvent 里
val window = OverlayUI.registerWindow("my-window", content).apply {
    // 仅在游戏内可用（避免菜单界面乱入）
    availability = { Vars.state.isGame }
    resizable = true
    autoHeight = true
}
```

注意：OverlayUI 会把 `content` 包装进自己的 Window 外壳里，窗口标题、设置按钮、关闭按钮等都由 OverlayUI 管理。

---

## 4. Java 模组的推荐写法（不强依赖 MindustryX：反射 + 回退）

很多模组不想把 MindustryX 作为编译期依赖（避免玩家没装 MindustryX 就崩溃）。这时建议：

1) **运行时检测**：是否安装了 MindustryX。  
2) **反射注册**：若存在则 `OverlayUI.INSTANCE.registerWindow(name, table)`。  
3) **HUD 回退**：不存在则把 `table` 加到 `ui.hudGroup` 或 `Core.scene`。

### 4.1 检测 MindustryX 是否存在

```java
boolean hasMindustryX = Vars.mods != null && Vars.mods.locateMod("mindustryx") != null;
```

### 4.2 反射调用 OverlayUI.registerWindow

MindustryX 的 `OverlayUI` 在 Kotlin 中是 `object OverlayUI`，Java 侧会暴露为：
- `OverlayUI.INSTANCE` 单例字段
- `OverlayUI.INSTANCE.registerWindow(String, Table)` 方法

示例（省略异常处理）：

```java
Class<?> overlayUiClass = Class.forName("mindustryX.features.ui.OverlayUI");
Object overlayUi = overlayUiClass.getField("INSTANCE").get(null);
Method registerWindow = overlayUiClass.getMethod("registerWindow", String.class, Table.class);

Object window = registerWindow.invoke(overlayUi, "my-window", contentTable);
```

### 4.3 只在游戏内可用（availability）

OverlayUI.Window 有一个 `availability`（Kotlin 属性，Java 会表现为 setter 方法）。  
在不引入 MindustryX 的情况下，最稳妥做法是 **可选地**反射调用：

```java
// window.getClass().getMethod("setAvailability", Prov.class).invoke(window, (Prov<Boolean>)() -> Vars.state.isGame());
```

注意：availability 的类型是 `arc.func.Prov<Boolean>`（不是 `Boolp`）。

### 4.4 同步你自己的开关到 OverlayUI 的 enabled/pinned

OverlayUI 的“窗口启用状态”由 `window.data.enabled` 控制（同时 pinned 影响是否常驻显示）。  
如果你希望你的模组设置（例如 `my-window-enabled`）控制 OverlayUI 面板显示，可以在更新循环里同步：

```java
// 伪代码：当你的设置从 false->true 时，设置 data.enabled=true 且 data.pinned=true
```

这样玩家不必先打开 OverlayUI 再点 “+” 添加窗口，体验更像“开关即显示”。

---

## 5. 窗口内容 Table 的设计建议

1) **不要自己管理位置/尺寸**（OverlayUI 模式下）
   - 位置、大小交给 OverlayUI；你的 `Table` 只负责内容布局与数据刷新。
2) **可见性与刷新逻辑**
   - 你的 `act()` 里可以根据 `Vars.state.isGame()` 判断是否更新内容；但不要强制 `setPosition(...)`。
3) **背景**
   - OverlayUI.Window 自己会绘制窗口壳（标题栏等），你的内容背景可选。
   - 如果你仍想要内容背景，注意不要与窗口壳冲突（例如黑底透明即可）。
4) **触摸/鼠标**
   - 如果你只是展示信息，`touchable = Touchable.disabled` 能减少误触。
   - 如果你有按钮/滚动等交互，使用 `Touchable.childrenOnly` 并保证 UI 元素可点击。

---

## 6. 与 WayzerMapBrowser 的一致做法（参考）

WayzerMapBrowser 的逻辑是：

- 如果检测到 MindustryX：`OverlayUI.INSTANCE.registerWindow("wayzer-maps", table)`  
- 否则：`Core.scene.add(table)` 并自己做拖拽/定位

PGMM 也采用了相同思路：优先 OverlayUI，缺失时 HUD 回退。

---

## 7. PGMM 当前实现位置（可直接抄结构）

PGMM 的 OverlayUI 对接代码在：
- `src/main/java/powergridminimap/PowerGridMinimapMod.java`：
  - `ensurePowerTableAttached()`：优先注册到 OverlayUI，否则回退 HUD
  - `MindustryXOverlayUI`：反射包装（检测、registerWindow、enabled/pinned 同步）
  - `PowerTableOverlay.setHostedByOverlayUI(true/false)`：切换“是否由 OverlayUI 托管”，避免自己强行锚定坐标

---

## 8. 常见坑

1) **用太早注册**
   - UI 还没初始化时注册会失败；建议放到 `ClientLoadEvent` 后，并用 `Time.runTask(...)` 延迟一次重试。
2) **Table 自己强行 setPosition**
   - 在 OverlayUI 模式下会和 Window 的拖拽/吸附冲突；务必关掉你自己的定位逻辑。
3) **把 MindustryX 当 compile 依赖**
   - 玩家没装 MindustryX 会 ClassNotFound 崩；除非你明确要求依赖，否则用反射更稳。

