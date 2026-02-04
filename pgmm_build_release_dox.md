# PGMM 构建 / 打包 / Release 工作流说明

本文档说明如何在本仓库内构建 PGMM，以及 GitHub Release 的自动化流程（tag 触发上传产物）。

---

## 1) 本地构建（Windows / PowerShell）

在仓库根目录执行：

```powershell
.\gradlew.bat zipMod
.\gradlew.bat jarMod
```

输出：

- `dist/powergrid-minimap.zip`
- `dist/powergrid-minimap.jar`

两者内容等价（都包含编译后的 class 与 `src/main/resources` 资源）。Mindustry 安卓端通常更偏好 `.jar`，但 `.zip` 也能用。

---

## 2) 版本号维护

版本需要保持一致：

- `build.gradle`：`version = "x.y.z"`
- `src/main/resources/mod.json`：`"version": "x.y.z"`

建议：每次发布前先修改版本号，再本地构建生成 `dist/` 产物。

---

## 3) GitHub Actions 自动 Release

工作流文件：

- `.github/workflows/release.yml`

触发条件：

- push tag：`v*`（例如 `v1.8.0`）

行为：

1) checkout 代码
2) 创建 GitHub Release
3) 上传：
   - `dist/powergrid-minimap.zip`
   - `dist/powergrid-minimap.jar`

注意：

- 该工作流不会帮你本地生成 `dist/`，因此发布前应确保仓库里提交了最新的 `dist/*`（或者你修改工作流让 CI 自己构建再上传）。

---

## 4) 推荐发布步骤（当前仓库习惯）

1) 更新版本号（`build.gradle` + `mod.json`）
2) 本地构建（`zipMod` + `jarMod`）确保 `dist/` 更新
3) commit + push `main`
4) 打 tag 并 push：

```powershell
git tag -a vX.Y.Z -m "vX.Y.Z"
git push origin vX.Y.Z
```

随后 Actions 会自动生成 Release 并附带产物。
