echo "enter the dir"
read dir
mkdir $dir
echo "enter the user dir"
read  user_dir
mkdir $dir/$user_dir
echo "enter the data"
read data
echo $data>> $dir/$user_dir/"Main.java"


