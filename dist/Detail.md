# Detail: dist

## 这一层在做什么
dist 是对外分发目录，也是最接近玩家实际拿到手文件的层。

## 这一层直接包含什么
### 直接子目录
- 本层没有直接子目录。

### 直接文件
- Power-Grid-Minimap-.jar（226.16 KB）
- Power-Grid-Minimap-.zip（226.16 KB）
- powergrid-minimap.jar（227.58 KB）
- powergrid-minimap.zip（227.58 KB）

## 这一层如何实现自己的职责
构建脚本中的 copy 任务会把 build/libs 里的归档复制到这里，CI 的 Release 上传也直接抓取这里的 jar 和 zip。当前目录里既有规范命名产物，也有像历史遗留的 Power-Grid-Minimap-.jar/.zip。

## 这一层与其他层级的关系
它向上承接 build/libs，向外连接玩家安装、群文件分发和 GitHub Release 附件。README 的安装说明对应的就是这里这种文件。

## 阅读这一层时要注意什么
这层不是源码，而是派生的最终交付视图。多个命名风格并存通常意味着发布命名策略发生过调整。
