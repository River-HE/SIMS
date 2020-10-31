#!/bin/bash


INSTALL_HOME=/home/app/sims

echo '>>>>>>>>>> 部署 Student Information Management System (SIMS) 开始'

if [ ! -d $INSTALL_HOME ]; then
	echo "安装目录 ${INSTALL_HOME} 不存在，现在创建目录..."
	mkdir -p $INSTALL_HOME
else
	echo "安装目录 ${INSTALL_HOME} 已存在，无需创建目录..."
fi
echo '--------------------------------------------------------------------------------------------------------------------'


echo '>>>>>>>>>> 创建临时目录 build_temp'
mkdir build_temp
cd build_temp
echo '--------------------------------------------------------------------------------------------------------------------'


echo '>>>>>>>>>> 从 GitHub 拉取代码'
git clone https://github.com/River-HE/SIMS.git
echo '>>>>>>>>>> 查看目录结构'
find -maxdepth 10
echo '--------------------------------------------------------------------------------------------------------------------'


echo '>>>>>>>>>> maven 清理并打包项目'
cd SIMS
mvn clean package -Dmaven.test.skip=true
echo '--------------------------------------------------------------------------------------------------------------------'

echo '>>>>>>>>>> 复制jar包到安装目录'
cp target/*.jar $INSTALL_HOME/sims.jar
echo '--------------------------------------------------------------------------------------------------------------------'

echo '>>>>>>>>>> 删除build_temp目录'
cd ../..
rm -r build_temp
echo '--------------------------------------------------------------------------------------------------------------------'

echo '>>>>>>>>>> 检查sims项目是否启动'
pid=`ps -ef|grep sims|grep -v grep|awk '{print $2}' `
if [ -n "${pid}" ]; then
	echo ">>>>>>>>>> sims项目已经启动，强制关闭sims"
	kill -9 $pid
fi
echo '--------------------------------------------------------------------------------------------------------------------'

echo '>>>>>>>>>> 后台启动sims'
cd $INSTALL_HOME
nohup java -Djasypt.encryptor.password=yangxm1997 -jar sims.jar >> Sims_System_Out.log 2>&1 &
sleep 10s
echo '--------------------------------------------------------------------------------------------------------------------'

echo '>>>>>>>>>> 检查sims是否启动成功'
pid=`ps -ef|grep sims|grep -v grep|awk '{print $2}' `
if [ -n "${pid}" ]; then
	echo ">>>>>>>>>> sims项目已经启动，进程号是：${pid}"
else
	echo '>>>>>>>>>> sims项目未启动，请查看日志...'
fi
echo "日志路径：${INSTALL_HOME}/Sims_System_Out.log"
echo '--------------------------------------------------------------------------------------------------------------------'

echo '>>>>>>>>>> 部署 Student Information Management System (SIMS) 结束'