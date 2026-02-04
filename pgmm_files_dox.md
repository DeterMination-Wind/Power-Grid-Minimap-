# PGMM 文件结构说明（Power Grid Minimap）

本文档概览本仓库（`Power-Grid-Minimap-repo-clone`）的主要文件/目录职责，方便二次开发与排查问题。

> 说明：PGMM 是 Mindustry 客户端 Java 模组；本仓库根目录即“模组根目录（仓库根目录）”。

---

## 1) 顶层文件

- `README.md`
  - 面向玩家的说明（安装/功能简介）。
- `OverlayUI使用说明.md`
  - 面向开发者：如何把窗口接入 MindustryX 的 OverlayUI（反射 + 回退方案）。
- `build.gradle`
  - Gradle 构建脚本：`zipMod` / `jarMod` / 拷贝到 `dist/`。
- `settings.gradle`
  - Gradle 工程名称等。
- `LICENSE`
  - 许可证。

---

## 2) 目录说明

- `.github/workflows/release.yml`
  - GitHub Actions：push `v*` tag 时自动发 Release，上传 `dist/powergrid-minimap.zip` 与 `dist/powergrid-minimap.jar`。
- `dist/`
  - 发布产物目录（本地构建后自动更新）：
    - `powergrid-minimap.zip`：传统 Mindustry 模组包（桌面/安卓都可用）。
    - `powergrid-minimap.jar`：安卓更常用的 jar 形式（内容与 zip 等价）。
- `src/main/java/powergridminimap/`
  - 模组源码（核心逻辑/UI/绘制/反射集成）。
- `src/main/resources/`
  - `mod.json`：Mindustry 模组元数据（名称/版本/入口类等）。
  - `bundles/`：多语言文本资源（设置项名称/描述、toast、提示文字等）。

---

## 3) Java 源码文件职责（按重要性）

- `src/main/java/powergridminimap/PowerGridMinimapMod.java`
  - 模组主入口与绝大部分逻辑所在：
    - 缓存/扫描电网
    - 小地图/全屏地图叠加绘制
    - 缺电救援建议（正电岛多边形圈选、冲击反应堆禁用建议、去抖窗口）
    - Power Table（HUD 或 MindustryX OverlayUI）
    - 与 MI2 / MindustryX 的可选集成（反射）
    - 设置页注册（已改为 MindustryX 风格行组件）
- `src/main/java/powergridminimap/PgmmSettingsWidgets.java`
  - PGMM 的“MindustryX 风格”设置项 UI 组件：
    - Header / Check / Slider / Text 行组件（图标、背景、可换行标题、tooltip 描述）。
- `src/main/java/powergridminimap/PgmmTypes.java`
  - 一些数据结构与反射小工具（例如全屏地图对齐用的 `FullMinimapAccess`）。
- `src/main/java/powergridminimap/GithubUpdateCheck.java`
  - 启动后异步检查 GitHub 上 `mod.json` 的版本号，提示可更新（离线/失败静默跳过）。

