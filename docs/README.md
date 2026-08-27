# PGMM 文档

Power Grid Minimap（电网小地图，`powergrid-minimap`）的分类文档。文档以中文为主，功能名保留英文本名，便于与代码和设置界面对照。

## 从哪里开始

| 你是 | 从这里开始 |
| --- | --- |
| 玩家 | [DOC.md](../DOC.md)（功能一览与用法）与 [README.md](../README.md) |
| 想了解 PGMM 怎么实现的人 | [架构总览](architecture.md) |
| 想改 PGMM 代码 | [开发指南](development.md) 与[架构总览](architecture.md) |
| 准备发版 | [版本与发布](release.md) |
| 改完想验证 | [测试指南](testing.md) |
| 遇到不认识的词 | [术语表](glossary.md) |

## 文档地图

```text
docs/
|-- README.md          本页：文档导航
|-- architecture.md    架构总览：入口、生命周期、核心类、设置系统、Neon 聚合形态
|-- development.md     开发指南：环境、构建命令、代码风格、调试入口
|-- release.md         版本与发布：v* 版本号、构建产物链、CI、Release 资产规则
|-- testing.md         测试指南：手测清单（本仓库无自动化测试）
`-- glossary.md        术语表
```

## 相关文件

- [DOC.md](../DOC.md)：合并版功能文档（玩家视角）。
- `pgmm_*_dox.md`（仓库根目录）：rescue / overlays / settings / api / build / files 各主题的原始说明文档。
- [RELEASE_NOTES.md](../RELEASE_NOTES.md)：逐版本更新日志。
- [AGENTS.md](../AGENTS.md)：仓库维护约束。
- [OverlayUI使用说明.md](../OverlayUI使用说明.md)：MindustryX OverlayUI 窗口行为说明。

## 维护约定

- 玩家视角的"怎么用"写在 DOC.md；本目录描述"怎么实现、怎么构建、怎么发版"。同一主题两边都出现时，DOC.md 从操作视角写，本目录从代码结构写。
- 设置项与文案以 bundle key（`pgmm.*` / `setting.pgmm.*`）为准，文档只写默认值与所在分组。
- 默认关闭的功能（缺电救援建议、电力表、MI2U 叠加、联机兼容标记等）必须注明默认关闭，避免误以为功能缺失。
