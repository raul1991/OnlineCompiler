echo "Please enter the path to examples dir" 
read dir_examples
export CLASSPATH="$dir_examples"
echo "Please enter the path to users dir" 
read dir


echo "Please enter the package name" 
read pack


javac "${dir}/Main.java"
java "users${pack}.Main"