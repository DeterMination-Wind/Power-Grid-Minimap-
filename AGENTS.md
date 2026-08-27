# AGENTS.md - Power-Grid-Minimap-repo-clone 模组说明

## 文件结构（当前仓库）
```text
Power-Grid-Minimap-repo-clone/
|-- .github/
|   \-- workflows/
|       |-- pr.yml
|       \-- release.yml
|-- Detail.md
|-- bin/
|   \-- main/
|       |-- bundles/
|       |-- powergridminimap/
|       \-- mod.json
|-- docs/
|   \-- bek-feedback-group.png
|-- gradle/
|   \-- wrapper/
|       |-- gradle-wrapper.jar
|       \-- gradle-wrapper.properties
|-- src/
|   \-- main/
|       |-- java/
|       \-- resources/
|-- .gitignore
|-- AGENTS.md
|-- build.gradle
|-- DOC.md
|-- gradlew
|-- gradlew.bat
|-- LICENSE
|-- mod.hjson
|-- mod.json
|-- OverlayUI使用说明.md
|-- pgmm_api_dox.md
|-- pgmm_build_release_dox.md
|-- pgmm_files_dox.md
|-- pgmm_overlays_dox.md
|-- pgmm_rescue_dox.md
|-- pgmm_settings_dox.md
|-- README.md
|-- RELEASE_NOTES.md
\-- settings.gradle
```

## 维护约束
- 保持 Java 8 兼容（如本项目包含 Java 源码）。
- 变更优先聚焦性能与可读性，不做无关重构。
- 用户可见文案优先走 bundle/资源文件，不硬编码。

命令操作请使用 PowerShell 7（`pwsh`）。

## 文档索引
- `docs/README.md` 是文档导航；架构总览、开发指南、版本与发布、测试指南、术语表都在 `docs/` 下。
- 玩家视角的功能说明在 `DOC.md`（合并版）与根目录 `pgmm_*_dox.md` 系列原始文档；逐版本日志在 `RELEASE_NOTES.md`。

## Neon 聚合形态（bekBundled）
- 本仓库独立发行，同时作为 Neon 聚合模组的子模组（id：`pgmm`）被打包。
- 主类必须保留 `public static boolean bekBundled` 与 `public void bekBuildSettings(SettingsMenuDialog.SettingsTable)` 两个契约成员；`registerSettings()` 在 `bekBundled == true` 时早退，不得移除该守卫。
- 不在本仓库引入对 `bektools` 或 Neon 其他模块的代码依赖；聚合侧只依赖上述契约成员。

## 测试与验证清单
- 无自动化测试：提交前至少跑 `.\gradlew.bat compileJava`；改动打包相关内容后跑 `.\gradlew.bat clean deploy` 并核对 `dist/` 产物。
- 手测清单见 [docs/testing.md](docs/testing.md)；发版步骤与 Release 资产安全（只放一个可安装 `.jar`）见 [docs/release.md](docs/release.md)。
- 提交信息沿用现有风格：功能改动如 `Power Grid Minimap: <摘要>`，发版如 `release: vX.Y.Z`，文档如 `docs: <摘要>`。
