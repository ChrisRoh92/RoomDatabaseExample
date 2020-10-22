# RoomDatabaseExample - Part 1
## Instructions
If you want to do this Part of the Room Database Tutorial, beginn with the Branch **Master** and do the tasks, given in the section below.

## Tasks of this Part
1) Add RecyclerView to fragment_main.xml
2) Create item_voc.xml as childlayout for the RecyclerView
3) Create the VocListAdapter (RecyclerView.Adapter) for all available Vocabularies
4) Implement the RecyclerView in MainFragment.kt
5) Create a new Drawable for the FloatingActionButton

#### How the Layout of the MainFragment should look like
<img src="https://github.com/ChrisRoh92/RoomDatabaseExample/blob/Part_1/screenshot/MainFragment_Preview.png?raw=true" width="250">

## Solution
### 1) Add RecyclerView to **fragment_main.xml**

```
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/main_rv"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        />
```


### 2) Create **item_voc.xml** as child layout for the RecyclerView
<details>
  <summary>Click to see the full XML Code for the item_voc.xml</summary>
  
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <ImageView
        android:id="@+id/item_image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:padding="8dp"
        app:layout_constraintBottom_toBottomOf="@+id/item_sub"
        app:layout_constraintDimensionRatio="1:1"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@+id/item_main"
        app:srcCompat="@drawable/ic_open" />

    <View
        android:id="@+id/divider"
        android:layout_width="1dp"
        android:layout_height="0dp"
        android:layout_marginStart="8dp"
        android:background="?android:attr/listDivider"
        app:layout_constraintBottom_toBottomOf="@+id/item_image"
        app:layout_constraintStart_toEndOf="@+id/item_image"
        app:layout_constraintTop_toTopOf="@+id/item_image" />

    <TextView
        android:id="@+id/item_main"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:text="Foreign Word"
        android:textSize="18sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/divider"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/item_sub"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginEnd="8dp"
        android:layout_marginBottom="8dp"
        android:text="Native Word"
        android:textColor="@color/colorPrimary"
        android:textSize="10sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="@+id/item_main"
        app:layout_constraintTop_toBottomOf="@+id/item_main" />
</androidx.constraintlayout.widget.ConstraintLayout>
```
</details>

### 3) Create the **VocListAdapter.kt** as RecyclerViewAdapter
```
class VocListAdapter(var content:ArrayList<String>):RecyclerView.Adapter<VocListAdapter.ViewHolder>()
{
    // Array with the different status drawables:
    private val statusDrawables = arrayOf(R.drawable.ic_open,R.drawable.ic_work,R.drawable.ic_done)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_voc,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: VocListAdapter.ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val tvMain:TextView = itemView.findViewById(R.id.item_main)
        val tvSub:TextView = itemView.findViewById(R.id.item_sub)
        val image:ImageView = itemView.findViewById(R.id.item_image)
    }

}
```
### 4) Implement the RecyclerView in MainFragment.kt
In the next step, we implement the RecyclerView within the **MainFragment.kt**. Therefore we create the method **initRecyclerView(...)**. To test the appearance of the RecyclerView, we create an empty dummy Arraylist.
On the top, we create global variables for the RecyclerView and the VocListAdapter:
```
private lateinit var rv:RecyclerView
private lateinit var adapter:VocListAdapter
```
Then, we create the already mentioned **initRecyclerView(...)**, which will be called on the **OnViewCreated(...)** method:
```
private fun initRecyclerView()
{
        rv = rootView.findViewById(R.id.main_rv)
        val content = ArrayList<String>(List(25) {""})
        adapter = VocListAdapter(content)
        rv.adapter = adapter
}
```
Before we test the app, we want to change the icon in the FloatingActionButton:
### 5) Create a new Drawable for the FloatingActionButton
First, we have to create a Drawable. Therefore you have to right click on the *res* folder. Then click on *New* right on the top and the click on *Vector Asset*. Then the **Asset Studio** window appears. In this window, you have to click on the Button, on the right of the text *Clip Art:*. A new menu, *Select Icon*, appears, where you can search for icons. We want the *add* icon. So type in the SearchView, right on the upper left corner, *add* and select the icon and click afterwards on *OK*, on the menu (Lower left). In the *Asset Studio* you click *next* and then on *finish*. Now, you should have created the **Add Icon** succesfully.

Now, change to the code view of **activity_main.xml** and update the **FloatingActionButton** block up to:
```
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="@dimen/fab_margin"
        app:srcCompat="@drawable/ic_add" />
```
If you named the icon different, like in the code snippet above, just use your name for the *add icon*.

Now, you can run the app the first time. Have fun and see you in the next Part :)
