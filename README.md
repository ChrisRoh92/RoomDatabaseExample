# RoomDatabaseExample - Part 2

## Tasks
1) Add the Gradle Dependencies

2) Create the Voc Entity Class

3) Create the Data Access Object (DAO)

4) Create the VocDataBase Class
## Solution
### 1) Add Gradle Dependencies:
1.1) Add the following code snippets to the **dependencies {...}** Field

```
dependencies {

....

def room_version = "2.2.5"

  implementation "androidx.room:room-runtime:$room_version"
  kapt "androidx.room:room-compiler:$room_version"

  // optional - Kotlin Extensions and Coroutines support for Room
  implementation "androidx.room:room-ktx:$room_version"
  
}
  ```
Up-To-Date Version-Number @ [Android Room Developer](https://developer.android.com/topic/libraries/architecture/room)

1.2) Add the following line on top of build.gradle (App):
```
apply plugin: 'kotlin-kapt'
```
### 2) Create Data Class Voc with @Entity Annotation
```
@Entity
data class Voc(
      @PrimaryKey(autoGenerate = true) var id:Long,
      var nativeWord:String,
      var foreignWord:String,
      var date:String,
      var status:Int)
```

