加上就能用了思密达
compile 'com.seekting:demolib:1.0.1'


具体用法:
manifest不要定义入口，因为lib里已经有入口.可能为每个demo加上描述和标题，每个demo是以activity为单位

```java
public class BookActivity extends Activity {
    static {
        MainActivity.putTitle(BookActivity.class,"书上的demo");
        MainActivity.putDesc(BookActivity.class,"书上的demo");
    }
    //代码...

```
以下是截图
![device-2017-06-13-162726.png](device-2017-06-13-162726.png)