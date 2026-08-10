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
