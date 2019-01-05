#!/bin/bash
###################################
#环境变量及程序执行参数
#需要根据实际环境以及Java程序名称来修改这些参数
###################################
#JDK所在路径
JAVA_HOME="/opt/java/jdk-11.0.1"

#Ubuntu版本信息
SYSTEM_VERSION=`head -n 1 /etc/issue`

#Java程序所在的目录
APP_NAME=eureka-server
APP_VERSION=1.0.0.SNAPSHOT
APP_HOME=/opt/shumei/$APP_NAME;export APP_HOME
APP_LIB=$APP_HOME/lib
APP_CONF=$APP_HOME/conf

#需要启动的Java主程序
APP_LAUNCHER=$APP_LIB/$APP_NAME-$APP_VERSION.jar

#Java虚拟机启动参数
JAVA_OPTS=""

###################################
#(函数)判断程序是否已启动
#
#说明：
#使用JDK自带的JPS命令及grep命令组合，准确查找pid
#jps 加 l 参数，表示显示java的完整包路径
#使用awk，分割出pid ($1部分)，及Java程序名称($2部分)
###################################
#初始化app_pid变量（全局）
is_root=
app_pid=0

check_pid() {
    app_process=`ps -ef | grep $APP_NAME | grep -v "grep"`

    if [ -n "$app_process" ]; then
        app_pid=`echo $app_process | awk '{print $2}'`
    else
        app_pid=0
    fi
}

check_root() {
    if [ `whoami` == "root" ];then
        echo "Root can not startup the app!"
        exit 1
    fi

    if [ `id -u` -eq 0 ];then
        echo "Root can not startup the app!"
        exit 1
    fi
}

###################################
#(函数)启动程序
#
#说明：
#1. 首先调用check_pid函数，刷新$app_pid全局变量
#2. 如果程序已经启动（$app_pid不等于0），则提示程序已启动
#3. 如果程序没有被启动，则执行启动命令行
#4. 启动命令执行后，再次调用check_pid函数
#5. 如果步骤4的结果能够确认程序的pid,则打印[OK]，否则打印[Failed]
#注意：echo -n 表示打印字符后，不换行
#注意: "nohup 某命令 >/dev/null 2>&1 &" 的用法
###################################
start() {
    check_root
    check_pid

    if [ $app_pid -ne 0 ]; then
        echo "================================"
        echo "warn: $APP_NAME already started! (pid=$app_pid)"
        echo "================================"
    else
        echo -n "Starting $APP_NAME ..."
        nohup $JAVA_HOME/bin/java $JAVA_OPTS -jar $APP_LAUNCHER >$APP_HOME/logs/piano.log 2>&1 &
        check_pid
        if [ $app_pid -ne 0 ]; then
            echo "(pid=$app_pid) [OK]"
        else
            echo "[Failed]"
        fi
    fi
}

###################################
#(函数)停止程序
#
#说明：
#1. 首先调用check_pid函数，刷新$app_pid全局变量
#2. 如果程序已经启动（$app_pid不等于0），则开始执行停止，否则，提示程序未运行
#3. 使用kill -9 pid命令进行强制杀死进程
#4. 执行kill命令行紧接其后，马上查看上一句命令的返回值: $?
#5. 如果步骤4的结果$?等于0,则打印[OK]，否则打印[Failed]
#6. 为了防止java程序被启动多次，这里增加反复检查进程，反复杀死的处理（递归调用stop）。
#注意：echo -n 表示打印字符后，不换行
#注意: 在shell编程中，"$?" 表示上一句命令或者一个函数的返回值
###################################
stop() {
    check_pid

    if [ $app_pid -ne 0 ]; then
        echo -n "Stopping $APP_NAME ...(pid=$app_pid) "
        kill -9 $app_pid
        if [ $? -eq 0 ]; then
            echo "[OK]"
        else
            echo "[Failed]"
        fi

        check_pid
        if [ $app_pid -ne 0 ]; then
            stop
        fi
    else
        echo "================================"
        echo "warn: $APP_NAME is not running"
        echo "================================"
    fi
}

###################################
#(函数)检查程序运行状态
#
#说明：
#1. 首先调用check_pid函数，刷新$app_pid全局变量
#2. 如果程序已经启动（$app_pid不等于0），则提示正在运行并表示出pid
#3. 否则，提示程序未运行
###################################
status() {
    check_pid

    if [ $app_pid -ne 0 ];  then
        echo "$APP_NAME is running! (pid=$app_pid)"
    else
        echo "$APP_NAME is not running"
    fi
}

###################################
#(函数)打印系统环境参数
###################################
info() {
    echo "System Information:"
    echo "****************************"
    echo ${SYSTEM_VERSION:0:18}
    echo `uname -a`
    echo
    echo `$JAVA_HOME/bin/java -version`
    echo "JAVA_HOME=$JAVA_HOME"
    echo "APP_NAME=$APP_NAME"
    echo "APP_VERSION=$APP_VERSION"
    echo "APP_HOME=$APP_HOME"
    echo "APP_LAUNCHER=$APP_LAUNCHER"
    echo "****************************"
}

###################################
#读取脚本的第一个参数($1)，进行判断
#参数取值范围：{start|stop|restart|status|info}
#如参数不在指定范围之内，则打印帮助信息
###################################
case "$1" in
    'start')
        start
        ;;
    'stop')
        stop
        ;;
    'restart')
        stop
        start
        ;;
    'status')
        status
        ;;
    'info')
        info
        ;;
    *)
    echo "Usage: $0 {start|stop|restart|status|info}"
    exit 1
esac