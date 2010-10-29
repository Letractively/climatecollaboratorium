export JAVA_HOME="/usr/java/i586/jdk1.6.0_11/"
export JRE_HOME="/usr/java/i586/jdk1.6.0_11/"
export LD_LIBRARY_PATH="/home/collab/pangaea_vensim/vensim_dll"
export JAVA_OPTS="-Dvensim_lib_name=vendll32 -Dvensim_model_path=/home/collab/pangaea_vensim/vensim_model/clearn.vmf"


${JAVA_HOME}/bin/java ${JAVA_OPTS} -cp lib/log4j-1.2.15.jar:lib/commons-logging-1.1.1.jar:lib/Vensim.jar:build/pangaea-core.jar org.climatecollaboratorium.pangaea.vensim.PangaeaConnection
