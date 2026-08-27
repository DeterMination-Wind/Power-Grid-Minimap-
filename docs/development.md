# 开发指南

环境、常用构建命令与调试入口。仓库级约束见根目录 [AGENTS.md](../AGENTS.md)，两者冲突时以 AGENTS.md 为准。

## 环境

- Gradle 用 JDK 17 运行（CI 即 Temurin 17）；源码必须保持 **Java 8 兼容**（`sourceCompatibility = 1.8`、`options.release.set(8)`），不得使用 8 以上的 API。
- Mindustry/Arc 编译类路径按序回退：工作区 `:core` 子项目 → `mindustryLibsDir` 属性指向的本地 libs 目录 → JitPack `v154.3`（`mindustryVersion` 属性可覆盖）。
- MindustryX 类是可选编译依赖：`../MindustryX-main/work/core/build/classes` 存在时自动加入 `compileOnly`，不存在也能构建。
- D8 探测顺序：`D8_PATH` 环境变量 → `ANDROID_SDK_ROOT` / `ANDROID_HOME` 下的 `build-tools` → 工作区根 `commandlinetools-win-*` 目录。都没有时 `dexAndroid` 直接失败。
- Windows 下命令请用 PowerShell 7（`pwsh`）。

## 常用命令

```powershell
.\gradlew.bat deploy        # 合并产物：桌面+安卓 jar/zip，发版前必跑
.\gradlew.bat zipMod        # 仅桌面 zip（无 dex，中间产物）
.\gradlew.bat jarAndroid    # 仅安卓 jar（含 classes.dex）
.\gradlew.bat compileJava   # 只编译检查
```

`deploy` = `jarMerged` + `zipMerged`；`jarMerged` 内部先跑 `d8InputJar` → `dexAndroid`。产物链与自动复制目标见 [release.md](release.md)。

## 代码风格要点

- 遵循 Mindustry 官方风格：左花括号随行，`if(...)` 同行书写，4 空格缩进，UTF-8。
- 设置 key 前缀 `pgmm-`，bundle key 前缀 `pgmm.*` / `setting.pgmm.*`；用户可见文案不硬编码，新增 key 需同步补 `bundle.properties` 与 `bundle_zh_CN.properties`（其余语言可后续跟进）。
- 功能以主类内部类组织，跨类共享的数据结构放 `PgmmTypes.java`；主类已接近 4200 行，新功能优先拆独立文件。

## 调试建议

- 客户端指令：`/pgmm-restart`（清缓存重挂）、`/pgmm-rescan`（立即重扫）、`/pgmm-mi2 refresh`（重找 MI2U）。
- F8 控制台：先 `pgmm.help()` 看可用方法；`pgmm.restart()` 等价于软重启模组。
- 日志前缀统一 `PGMM:`；挂载失败（MI2U / OverlayUI）会自动重试，看日志确认走的是哪条降级路径。
- 改动 MI2U 对齐相关代码时，用非正方形地图 + 缩放小地图验证（历史 bug 集中于此，见 RELEASE_NOTES v1.18.x）。
