首先在gradle 里加上
compile 'com.seekting:demolib:1.0.3'

本lib会自动为你添加一个MainActivity，该Activity会把你的所有其它Activity罗列出来<br/>
所以你的manifest不要定义程序入口，因为lib里已经有入口


你的每一个demo必需也是一个Activity，并且可以为这些demo启名字。

启名字的方法：
可能为每个demo加上描述和标题，每个demo是以activity为单位

```java
public class BookActivity extends Activity {
    static {
        MainActivity.putTitle(BookActivity.class,"书上的demo");
        MainActivity.putDesc(BookActivity.class,"书上的demo");
    }
    //代码...

```


同样你也可以这样写，它会把标题和描述展示在demo列表里
```java
@Demo(title = "测试title", desc = "测试desc")
public class TestActivity extends Activity {

```
以下是截图
![device-2017-06-13-162726.png](device-2017-06-13-162726.png)