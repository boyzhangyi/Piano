## 开启ens33网卡

- 方法1

  进入桌面后，点击右上角Wired Enable network

- 方法2
  ```shell
  sudo ifconfig ens33 192.168.136.134
  sudo service network-manager restart
  ```

## 将默认的dash shell改成bash shell

此方法可以解决开机启动/etc/rc.local失效问题

- 方法1

  ```shell
  sudo dpkg-reconfigure dash
  ```
  然后在软终端弹出的界面选择no

- 方法2

  ```shell
  sudo rm /bin/sh
  sudo ln -s /bin/bash /bin/sh
  ```

## Ubuntu 16.04开启启动服务设置

- 步骤1

  设置打印启动日志

  ```shell
  sudo vi /etc/rc.local
  ```

  打开文件后添加以下三行

  ```shell
  exec 2> /tmp/rc.local.log
  exec 1>&2
  set -x
  ```

- 步骤2

  - 检查/etc/rc.local文件执行权限

  ```shell
  ll /etc/rc.local
  ```

  - 若无执行权限，添加后重启系统

  ```shell
  sudo chmod 755 /etc/rc/local
  ```

- 步骤3

  - 添加开机启动项

  ```shel
  sudo vi /etc/rc.local
  ```

  - 打开后在“exit 0”行前添加以下行

  ```shell
  su - eden -c "bash /opt/shumei/eureka-server/bin/piano.sh start"
  ```

  - 重启系统

- 步骤4

  若重启后服务没有启动，打开/etc/rc.local，将“#!/bin/sh”更改为“#!/bin/bash”
