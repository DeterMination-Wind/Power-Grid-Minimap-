# 版本与发布

PGMM 的版本号、构建产物链与 CI 分工。原则：版本号与构建都在本地完成，CI 在 tag 推送后构建并生成 GitHub Release。

## 版本号体系

- 语义化版本 `vX.Y.Z`（当前 `1.18.3`），发布 tag 用 `vX.Y.Z` 形式（如 `v1.18.3`）。
- 版本号写在三处，发版前必须一致：`mod.json`、`mod.hjson`、`build.gradle`（`version = "..."`）。改版本时三处同步改。

## 发布前本地步骤

1. 三处版本号改为目标版本并提交。
2. 本地完整构建：
   ```powershell
   .\gradlew.bat clean deploy
   ```
3. 核对产物：
   - `dist/powergrid-minimap.jar` 与 `dist/powergrid-minimap.zip`（桌面+安卓合并，含 `classes.dex`）。
   - 同步副本已自动写入 `../构建/powergrid-minimap/`，命名为 `powergrid-minimap-<version>.jar` / `.zip`。

## 构建产物链

```text
classes ──► d8InputJar ──► dexAndroid (d8 --min-api 14 --release)
   |
   |-- zipMod / jarMod        桌面包（无 dex，中间产物）
   |-- jarAndroid             安卓专用 jar（classes.dex + 资源）
   `-- jarMerged ──► zipMerged ──► deploy
        (classes + classes.dex + mod.json/hjson + README)
```

- `zipMod` / `jarMod` / `jarAndroid` / `jarMerged` / `zipMerged` 执行后会经 `copy*ToDist` / `copy*ToBuildFolder` 自动复制到 `dist/` 与 `../构建/powergrid-minimap/`（带版本号重命名）。
- 桌面专用 jar（不含 `classes.dex`）只能本地测试，不允许进入发布流程；安卓可用性以是否含 `classes.dex` 为准。

## CI（`.github/workflows/release.yml`）

触发：推送 `v*` tag 或手动 dispatch。流程：checkout → Temurin 17 → Android SDK（build-tools 34）→ `clean deploy` → `softprops/action-gh-release` 上传 `dist/*.jar` 与 `dist/*.zip` 并自动生成 Release Notes。另有 `pr.yml` 服务于 PR 检查。

## Release 资产安全规则

Mindustry 游戏内安装器会取 Release API 返回的**第一个 `.jar`**，且不按操作系统挑资产。因此：

- 一个 Release 有且只有一个 `.jar` 资产，即合并包 `powergrid-minimap.jar`（含 `mod.json` + 桌面主类 + `classes.dex`）；`.zip` 可一并附带。
- 严禁把 `powergrid-minimap-android.jar`、`*-d8-input.jar` 等中间产物传上 Release。
- 发布后复查：
  ```bash
  gh release view <tag> --json assets
  ```
  发现多余 `.jar` 立即删除。
