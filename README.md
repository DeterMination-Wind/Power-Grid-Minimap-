# 电网小地图 / Power Grid Minimap（Mindustry Mod）

## 反馈 / Feedback

【BEK辅助mod反馈群】：https://qm.qq.com/q/cZWzPa4cTu

![BEK辅助mod反馈群二维码](docs/bek-feedback-group.png)

在小地图/大地图上为每个电网分别着色，并在每个电网中心显示电力盈亏；支持电网断开告警与建议连接点。

Color each disconnected power network on the minimap/full map and show its power balance at the grid center; includes split alerts and reconnect hints.

## 构建

在 Mindustry-master 根目录执行：

```powershell
./gradlew.bat :powergrid-minimap:zipMod
```

输出 zip 在 `mods/powergrid-minimap/build/libs/`。

## 安卓 / Android

安卓端需要包含 `classes.dex` 的 mod 包。请下载 Release 中的 `powergrid-minimap-android.jar` 并放入 Mindustry 的 `mods` 目录。

本地构建（在本仓库根目录）：

```powershell
./gradlew.bat jarAndroid
```

输出：`dist/powergrid-minimap-android.jar`

## Build

Run this in the Mindustry-master repo root:

```powershell
./gradlew.bat :powergrid-minimap:zipMod
```

The zip will be in `Power-Grid-Minimap-repo-clone/build/libs/`.

## Android

Android requires a mod package that contains `classes.dex`. Download `powergrid-minimap-android.jar` from Releases and put it into Mindustry's `mods` folder.

Local build (from this repo root):

```powershell
./gradlew.bat jarAndroid
```

Output: `dist/powergrid-minimap-android.jar`
