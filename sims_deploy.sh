#!/bin/bash


INSTALL_HOME=/home/app/sims

echo '>>>>>>>>>> 部署 Student Information Management System (SIMS) 开始'

if [ ! -d $INSTALL_HOME ]; then
	echo ">>>>>>>>>> 安装目录 ${INSTALL_HOME} 不存在，现在创建目录..."
	mkdir -p $INSTALL_HOME
else
	echo ">>>>>>>>>> 安装目录 ${INSTALL_HOME} 已存在，无需创建目录..."
fi
echo '----------------------------------------------------------------------'


echo '>>>>>>>>>> 创建临时目录 build_temp'
mkdir build_temp
#进入build_temp目录
cd build_temp
echo '----------------------------------------------------------------------'


echo '>>>>>>>>>> 从 GitHub 拉取代码'
git clone https://github.com/River-HE/SIMS.git
echo '>>>>>>>>>> 查看目录结构'
find -maxdepth 10
echo '----------------------------------------------------------------------'


echo '>>>>>>>>>> maven 清理并打包项目'
#进入SIMS目录
cd SIMS
#使用maven对项目进行清理和打包，跳过测试案例运行
mvn clean package -Dmaven.test.skip=true
echo '----------------------------------------------------------------------'

echo '>>>>>>>>>> 复制jar包到安装目录'
cp target/*.jar $INSTALL_HOME/sims.jar
echo '----------------------------------------------------------------------'

echo '>>>>>>>>>> 删除build_temp目录'
#回到脚本所在的目录（上上一级目录）
cd ../..
#删除build_temp目录
rm -r build_temp
echo '----------------------------------------------------------------------'

echo '>>>>>>>>>> 检查sims项目是否启动'
pid=`ps -ef|grep sims.jar|grep -v grep|awk '{print $2}' `
if [ -n "${pid}" ]; then
	echo ">>>>>>>>>> sims项目已经启动，强制关闭sims"
	kill -9 $pid
fi
echo '----------------------------------------------------------------------'

echo '>>>>>>>>>> 后台启动sims'
#进入安装目录
cd $INSTALL_HOME
#后台启动sims.jar，控制台日志输出到Sims_System_Out.log
nohup java -Djasypt.encryptor.password=yangxm1997 -jar sims.jar >> Sims_System_Out.log 2>&1 &
#等待10秒
sleep 10s
echo '----------------------------------------------------------------------'

echo '>>>>>>>>>> 检查sims是否启动成功'
pid=`ps -ef|grep sims.jar|grep -v grep|awk '{print $2}' `
if [ -n "${pid}" ]; then
	echo ">>>>>>>>>> sims项目已经启动，进程号是：${pid}"
else
	echo '>>>>>>>>>> sims项目未启动，请查看日志...'
fi
echo ">>>>>>>>>> 日志路径：${INSTALL_HOME}/Sims_System_Out.log"
echo '----------------------------------------------------------------------'

echo '>>>>>>>>>> 部署 Student Information Management System (SIMS) 结束'