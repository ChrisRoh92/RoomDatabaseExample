# RoomDatabaseExample - Part 4
## Instructions
If you want to do this Part of the Room Database Tutorial, beginn with the Branch **Part_3** and do the tasks, given in the section below.

# Tasks
1) Create a simple Input Dialog with DialogFragment

    1.1) Modify the **style.xml**

    1.2) Create layout file - **dialog_input.xml** like the following example
    
    <img src="https://github.com/ChrisRoh92/RoomDatabaseExample/blob/Part_4/screenshot/InputDialog.PNG?raw=true" width=400>
    
    1.3) Create DialogFragment() - **DialogInput.kt**
    
    1.4) Save User Input to MainViewModel with fun **insert(voc:Voc)**   
    
    1.5) Connect the FloatingActionButton @ **MainActivity.kt** with the **DialogInput.kt**






2) Register Observer on the VocList LiveData and Update VocListAdapter
    
    2.1) Create MainViewModel Instance
    
    2.2) Update VocListAdapter with method **fun updateContent(..)** and change ArrayList Type from **String** to **Voc**
    
    2.3) Override ***OnBindViewHolder(...)*** @ **VocListAdapter.kt**
    
    2.4) Implement Observer @ **MainFragment.kt**

# Solution
## 1) Create a simple Input Dialog with DialogFragment
### 1.1) Modify the **style.xml**
To insert new vocabularies, we use a DialogFragment. To get a nice looking Dialog, with transparent Background, we have to create a new style for the DialogFragment.

```
<style name="FullScreenDialog" parent="AppTheme">
        <item name="windowNoTitle">true</item>
        <item name="android:layout_width">fill_parent</item>
        <item name="android:layout_height">fill_parent</item>
        <!-- No backgrounds, titles or window float -->
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:backgroundDimEnabled">true</item>
        <item name="android:backgroundDimAmount">0.7</item>
        <item name="android:windowIsFloating">false</item>
</style>
```

### 1.2) Create the Layout-File for the Input-Dialog:
Please try it first on your own, before you take a look to the solution, given below:
[dialog.input.xml]: https://github.com/ChrisRoh92/RoomDatabaseExample/blob/Part_4/app/src/main/res/layout/dialog_input.xml


### 1.3) Create DialogFragment() - **DialogInput.kt**

A good way to show custom dialogs is to usw DialogFragment(). Below you see what you have to do, if you want to implement a DialogFragment:
[InputDialog.kt]: https://github.com/ChrisRoh92/RoomDatabaseExample/blob/Part_4/app/src/main/java/com/example/roomdatabaseexample/main/DialogInput.kt

To use the new style **FullscreenDialog** you have to call the **setStyle(...)** method in onCreate(savedInstanceState: Bundle?):

```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NO_FRAME,
            R.style.FullScreenDialog)

    }
```

### 1.4) Save User Input to MainViewModel with fun **insert(voc:Voc)** 

To put new vocs into the Room Database, we have to create a method **insert(voc:Voc)**. Please consider, that you have created an instance of MainViewModel in the **onViewCreated(view: View, savedInstanceState: Bundle?)** method:

```
private fun saveData()
    {
        if(!TextUtils.isEmpty(etForeign.editText?.text.toString()) && !TextUtils.isEmpty(etNative.editText?.text.toString()))
        {
            mainViewModel.insert(etNative.editText?.text.toString(),etForeign.editText?.text.toString())
            Toast.makeText(requireContext(),"Voc inserted in Database",Toast.LENGTH_SHORT).show()
            dismiss()
        }
        else
        {
            Toast.makeText(requireContext(),"Please insert data in both Fields!",Toast.LENGTH_SHORT).show()
        }
}
```

### 1.5) Connect the FloatingActionButton @ **MainActivity.kt** with the **DialogInput.kt**

In the last step, to display the dialog, we have to create an OnClickListener for the FloatingActionButton in the MainActivity's **onCreate(...)*** method:

```
findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val dialog = DialogInput()
            dialog.show(supportFragmentManager,"New Input")
        }
```

## 2) Register Observer on the VocList LiveData and Update VocListAdapter

### 2.1) Create MainViewModel Instance in **MainFragment.kt**
To create an instance of the MainViewModel use the following lines of code within the **onViewCreated(...)** method. Therefore create a global variable for the mainViewModel:

```
mainViewModel = ViewModelProvider(requireActivity(),MainViewModelFactory(requireActivity().application))
                    .get(MainViewModel::class.java)
```

### 2.2) Update VocListAdapter with method **fun updateContent(..)** and change ArrayList Type from **String** to **Voc**

If the Observer reports a detected change in the LiveData, we want to update the RecyclerView. Therefore, we need a method, which updates the content and notifies the Adapter, that the data has changed. Therefore we create the **updateContent(...)** method:

```
fun updateContent(content:ArrayList<Voc>)
{
    this.content = content
    notifyDataSetChanged()
}
```

Because we now want to use the Voc Object, we have to change the data type of the Arraylist content from **String** to **Voc**. Therefore, change the type of the Arraylist like in the following code snippet:

```
class VocListAdapter(var content:ArrayList<Voc>):RecyclerView.Adapter<VocListAdapter.ViewHolder>()
```

### 2.3) Override ***OnBindViewHolder(...)*** @ **VocListAdapter.kt**

Now, we want to get the data of the vocs in the ArrayList "content". To display the informations, we have to override the **OnBindViewHolder(...)** method:

```
override fun onBindViewHolder(holder: VocListAdapter.ViewHolder, position: Int)
{
    val voc = content[position]
    holder.tvMain.text = voc.foreignWord
    holder.tvSub.text = voc.nativeWord
    holder.image.setImageResource(statusDrawables[voc.status])
}
```

In the onBindViewHolder method, we set the informations of each individual voc object, to the different views of the RecyclerView Item.

### 2.4) Implement Observer @ **MainFragment.kt**

For the last step, we have to implement an Observer in the MainFragment, so we get informed, if there are any changes in the LiveData for the Voc List. Therefore, implement in the **onViewCreated(...)** method, the observer, right after the instantiation of the mainViewModel:

```
mainViewModel = ViewModelProvider(requireActivity(),MainViewModelFactory(requireActivity().application)).get(MainViewModel::class.java)
mainViewModel.getLiveVocList().observe(viewLifecycleOwner, Observer { items ->
    adapter.updateContent(ArrayList(items))
})
```

The first line, you should have already have from **2.1**

Now you can start the Emulator, and test your App.
