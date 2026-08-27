# 术语表

按主题归类的 PGMM 术语。每条说明它在 PGMM 语境下的准确含义。

## 项目形态

### PGMM / powergrid-minimap
Power Grid Minimap（电网小地图）的缩写；mod id、包名（`powergridminimap`）、日志前缀、设置 key（`pgmm-*`）均由此而来。

### bekBundled
主类上的静态布尔标记，表示"当前作为 Neon 聚合模组的子模组运行"。为 `true` 时跳过自建设置分类，改由 Neon 总设置页调用 `bekBuildSettings(...)` 接管。

### main / mainX
`mod.json` 的双入口：`main`（`PowerGridMinimapMod`）用于原版客户端；`mainX`（`PowerGridMinimapModX`）在 MindustryX 类加载器存在时优先加载，注入 MDtX 桥实现。业务逻辑全部在基类。

### 纯客户端（client-side）
全部功能只改本地显示，不要求服务器安装；联机中其他玩家只有在自己也装了 PGMM 时才能看到叠加效果。

## 电力与显示

### 电网（PowerGraph）
Mindustry 中由导线/节点连通的一组建筑构成的电力图。PGMM 按图分色，每图一个盈亏标记；被远距离连接拉得很"稀"的电网可聚成多块（ClusterInfo），各有一个中心。

### 电力盈亏（balance）
电网每秒净功率：`graph.getPowerBalance() * 60`。`> 0` 富余，`< 0` 缺电；随负载与电池充放电波动。

### 断网告警（SplitAlert）
大电网分裂（SplitWatcher 检测）后、若时间窗内出现负电，弹出提示并在地图上标记建议重连点（ReconnectHint）与连线；阈值 / 颜色 / 线宽可调。

### 救援建议（RescueAdvisor）
持续负电时给出的"恢复正电"建议：圈出可隔离的正电岛，或标记建议禁用的冲击反应堆（ImpactDisableHint）。制作中功能，默认关闭。

### 电力表（PowerTableOverlay）
以列表汇总大电网概览（当前盈亏、近期最低值等），默认关闭；MindustryX 下注册为 OverlayUI 窗口，原版回退为普通 HUD 元素。

## 集成

### 桥接口（mdtxcompat）
隔离 MindustryX 能力的接口族：`OverlayUiBridge` / `MarkerBridge` / `SchematicShareBridge`，各有 MindustryX 实现与 Noop 空实现；`LegacyMindustryXGuard` 拒绝过旧的 MDtX。

### MI2U
MI2-Utilities-Java 模组。PGMM 通过反射访问其小地图（`mi2u.ui.MinimapMindow`）把叠加画上去，未安装时静默降级；该叠加默认关闭。

### OverlayUI
MindustryX 的悬浮窗管理器。PGMM 的电力表可注册为其中一个窗口（可拖动/固定）；窗口可见性由 OverlayUI 自身设置控制，PGMM 只在开关变化时同步一次。

### 兼容标记（`<PGMM>(x,y)`）
联机时经聊天消息发送的标记文本（`<PGMM><标签>(x,y)`），让未装 PGMM 的客户端也能看到告警位置。默认关闭。

## 构建与发布

### D8 / classes.dex
把 Java 字节码转成安卓 `classes.dex` 的工具链。安卓加载 Java 模组必须含 dex；`deploy` 产出的合并 jar 已包含，桌面中间 jar（`jarMod`）不含，不能分发。

### bundle
Mindustry 的 i18n 文件（`bundle.properties` / `bundle_zh_CN.properties` 等，约 30 种语言），key 前缀 `pgmm.*` / `setting.pgmm.*`；用户可见文案不允许硬编码。
