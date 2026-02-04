# PGMM 设置项设计与风格说明（MindustryX 风格）

本文档说明 PGMM 的设置项组织方式、命名规则、以及与 MindustryX 设置风格同步的实现手法。

---

## 1) 设置存储

PGMM 使用 Mindustry 原生 `Core.settings` 存取配置：

- `Core.settings.defaults(key, def)`：注册默认值
- `Core.settings.getBool/getInt/getString(...)`：读取
- `Core.settings.put(key, value)`：写入

这些 key 以 `pgmm-` 开头（例如 `pgmm-enabled`）。

---

## 2) 设置 UI：MindustryX 风格行组件

实现文件：`src/main/java/powergridminimap/PgmmSettingsWidgets.java`

提供 4 类行组件（与 MindustryX/StealthPath 设置页类似）：

- `HeaderSetting`
  - 分组标题：`Styles.black3` 背景 + `Pal.accent` 标题色。
- `IconCheckSetting`
  - 开关：`Tex.button` 容器 + 左侧图标 + 可换行标题，`addDesc(...)` 提示描述。
- `IconSliderSetting`
  - 滑条：`Stack(slider, content)` 叠加，在滑条上方显示标题与当前值。
- `IconTextSetting`
  - 文本：左侧标题 + 右侧输入框（常用于 Hex 颜色）。

宽度策略：`prefWidth()` 根据屏幕宽度取一个上限，避免桌面过宽、移动端过窄。

---

## 3) 设置页入口与分组

设置页注册位置：`PowerGridMinimapMod.registerSettings()`

分组标题文案来自 bundles：

- `pgmm.section.basic`（基础）
- `pgmm.section.integration`（集成）
- `pgmm.section.alerts`（告警与标记）
- `setting.pgmm.rescue.section`（缺电救援）
- `setting.pgmm.powertable.section`（电力表）
- `pgmm.section.performance`（性能）

---

## 4) 文本资源（bundles）

文件：`src/main/resources/bundles/bundle*.properties`

主要类别：

- `setting.<key>.name/description`：原生设置项名称/描述
- `pgmm.*`：toast/提示文字/分组标题等
- `settingV2.overlayUI.pgmm-power-table.name`
  - MindustryX OverlayUI 读取的窗口标题（OverlayUI 侧用 `settingV2.overlayUI.<windowName>.name`）。

建议：新增设置项时，至少补 `bundle.properties`（英文）与 `bundle_zh_CN.properties`（中文）。

