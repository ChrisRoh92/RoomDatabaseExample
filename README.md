# RoomDatabaseExample - Part 5
## Instructions
If you want to do this Part of the Room Database Tutorial, beginn with the Branch **Part_4** and do the tasks, given in the section below.

# Tasks

1) Set clickable Background for the Item Layout **item_voc.xml**

2) Create an OnClickListener for each Item of the RecyclerView - show an entry
  
    2.1) Create an Interface OnItemClickListener **VocListAdapter.kt**
  
    2.2) Implement the Interface in **MainFragment.kt**
  
    2.3) Update the **InputDialog.kt**, that you can transfer an Voc Object, which will be default to **null**. Depending on the status of the Voc, we set the Texts of the EditTexts and call the **update(voc:Voc)** method, instead of the **insert(...)** method of the MainViewModel.
  
    2.4) Call the **InputDialog.kt** with a click on an item

3) Create an OnLongClickListener for each item of the RecyclerView - Delete an entry

    3.1) Create an Interface OnItemLongClickListener
  
    3.2) Create a simple AlarmDialog to confirm the delete action in **MainFragment.kt** and call the **fun delete(voc:Voc)** method to remove the entry, if user confirm delete  action
    
    3.3) Implement the Interface in **MainFragment.kt** and call the AlarmDialog to confirm the delete action
  
     
    
# Solution
## 1) Set clickable Background for the Item Layout **item_voc.xml**
If you want to get the typical OnClick Animation for each Item, you have to set the Background to "selectableItemBackground". Therefore, change to the Code view of **item_voc.xml** and add the following line of code to the ConstraintLayout on the top:

```
android:background="?attr/selectableItemBackground"
```

## 2) Create an OnClickListener for each item of the RecyclerView
In this section, we will create an Interface with a method, which will provide us the clicked position. With that, we can extract the Voc from the ArrayList "content" and use it to display the clicked item in the **InputDialog.kt**, where the user can change and save the values of each object.

### 2.1) Create an Interface OnItemClickListener in VocListAdapter.kt
First, you have to create a variable for the Interface **OnItemClickListener** 

```
private lateinit var mItemListener:OnItemClickListener
```

Then, you have to create the Interface:
```
interface OnItemClickListener
{
  fun setOnItemClickListener(pos:Int)
}

fun setOnItemClickListener(mItemListener:OnItemClickListener)
{
  this.mItemListener = mItemListener
}
```
In the first section, the interface **OnItemClickListener** is created. This interface has one method: **fun setOnItemClickListener(pos:Int)**, which need the clicked position. The second part, will be called from the MainFragment, which is necessary, to implement the method of the Interface.

Now, we have to give the ViewHolder this interface. Therefore, we change the **onCreateViewHolder(...)** method to:

```
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocListAdapter.ViewHolder
{
  val view = LayoutInflater.from(parent.context).inflate(R.layout.item_voc,parent,false)
  return ViewHolder(view,mItemListener)
}
```
As you can see, we now return a ViewHolder, with the inflated view plus the variable for the OnItemClickListener Interface. Therefore we have to change the ViewHolder to:

```
class ViewHolder(itemView: View,mItemListener:OnItemClickListener):RecyclerView.ViewHolder(itemView)
    {
        val tvMain:TextView = itemView.findViewById(R.id.item_main)
        val tvSub:TextView = itemView.findViewById(R.id.item_sub)
        val image:ImageView = itemView.findViewById(R.id.item_image)

        init {
            // Implement simple OnClickListener for each Entry
            itemView.setOnClickListener {
                mItemListener?.setOnItemClickListener(adapterPosition)
            }           
        }
    }
```
The class ViewHolder now gets a View, and the OnItemClickListener. In the next step we use the **init{...}** block to implement a simple OnClickListener to the itemView, which is the whole entry. From there we call the **setOnItemClickListener(...)** method and pass the current adapterPosition.

### 2.2) Implement the Interface in **MainFragment.kt**
Within the **initRecyclerView()** method we will now implement the OnItemClickListener:

```
adapter.setOnItemClickListener(object:VocListAdapter.OnItemClickListener{
  override fun setOnItemClickListener(pos: Int) {
  
    val dialog = DialogInput(adapter.content[pos])
    dialog.show(parentFragmentManager,"update Voc")
  }
})
```
As you can see, after a click on an entry was recognized, i call the the DialogInput. Simultaneously i pass the clicked Voc object. The goal now is, to update the **InputDialog.kt**, so it show the values of the clicked Voc object

### 2.3) Update the **InputDialog.kt**
In the first step, we modify the primary constructor. Because in **MainActivity.kt** we do not pass any Voc object, we set the default value to **null**:
```
class DialogInput(var voc: Voc? = null):DialogFragment()
```
To use this dialog for two purposes, to create and show/update an existing voc, we now have to check, if the voc in the constructor is null. Therefore, i check within the **onViewCreated(...)** method, if voc is null:
```
// Check if voc is not null:
if(voc != null)
{
  // setText from the passed voc object
  etForeign.editText?.setText(voc?.foreignWord)
  etNative.editText?.setText(voc?.nativeWord)
}
```
If the voc object is not null, we pass the content of the object to the two EditText objects.

In the next step, we have to modify the **saveData()** method. We have to distinguish, if we want to insert a new voc object, or if we want to update the passed voc object.
Therefore we check again, if voc is not null. If it is not null, we change the voc?.nativeWord and the voc?.foreignWord, with the values from the two EditTexts. After that we call the **update(voc:Voc)** method of the **mainViewModel**:
```
private fun saveData()
{
  if(!TextUtils.isEmpty(etForeign.editText?.text.toString()) && !TextUtils.isEmpty(etNative.editText?.text.toString()))
  {
    if(voc != null)
    {
      voc?.nativeWord = etNative.editText?.text.toString()
      voc?.foreignWord = etForeign.editText?.text.toString()
      mainViewModel.update(voc!!)
      Toast.makeText(requireContext(),"Voc updated in Database",Toast.LENGTH_SHORT).show()
    }
    else
    {
        mainViewModel.insert(etNative.editText?.text.toString(),etForeign.editText?.text.toString())
        Toast.makeText(requireContext(),"Voc inserted in Database",Toast.LENGTH_SHORT).show()
    }
    dismiss()
  }
  else
  {
    Toast.makeText(requireContext(),"Please insert data in both Fields!",Toast.LENGTH_SHORT).show()
  }
}
```
## 3) Create an OnLongClickListener for each item of the RecyclerView - Delete an entry
Similar to 2), we now want to create an OnItemLongClickListener, so we can detect, if the user wants to delete an entry: Therefore we follow mostly the same steps, as in **2.1)** already shown

### 3.1) Create an Interface OnItemLongClickListener
First, you have to create a variable for the Interface **OnItemLongClickListener** 

```
private lateinit var mItemLongListener:OnItemLongClickListener
```

Then, you have to create the Interface:
```
interface OnItemLongClickListener
{
  fun setOnItemLongClickListener(pos:Int)
}
fun setOnItemLongClickListener(mItemLongListener:OnItemLongClickListener)
{
  this.mItemLongListener = mItemLongListener
}
```
Now, we have to give the ViewHolder this interface. Therefore, we change the **onCreateViewHolder(...)** method to:
```
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocListAdapter.ViewHolder
{
  val view = LayoutInflater.from(parent.context).inflate(R.layout.item_voc,parent,false)
  return ViewHolder(view,mItemListener,mItemLongListener)
}
```
So the ViewHolder gets the view itself, the OnItemClickListener for showing/updating an entry, and the OnItemLongClickListener to delete an entry on a long click. Therefore, you now have to modify the primary constructor of the ViewHolder, so it expects an OnItemLongClicklistener. Additional, you have to create an OnLongClickListener within the **init{...}** block, where you call the **setOnItemLongClickListener(...)*** methode, which get also the current adapterPosition:
```
class ViewHolder(itemView: View,mItemListener:OnItemClickListener,mItemLongListener:OnItemLongClickListener):RecyclerView.ViewHolder(itemView)
{
  val tvMain:TextView = itemView.findViewById(R.id.item_main)
  val tvSub:TextView = itemView.findViewById(R.id.item_sub)
  val image:ImageView = itemView.findViewById(R.id.item_image)

  init {
    // Implement simple OnClickListener for each Entry
      itemView.setOnClickListener {
        mItemListener?.setOnItemClickListener(adapterPosition)
        }

        // Implement simple OnLongClickListener for each Entry:
        itemView.setOnLongClickListener {
          mItemLongListener?.setOnItemLongClickListener(adapterPosition)
          true
        }
    }
}
```

### 3.2) Create a simple AlarmDialog to confirm the delete action in **MainFragment.kt**
To prevent the user of accidentally deleting an entry, we want the user to confirm the delete action with a simple AlertDialg. Therefore we create the method **fun startAlarmDialog(voc:Voc)** which gets the clicked voc object. So in the case, the user wants to remove the voc from the database, we call the **remove(voc:Voc)** method in the **mainViewModel**. But first, we create a simple AlertDialog, like shown below:
```
private fun startAlarmDialog(voc: Voc)
{
  val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
  builder.apply {
    setMessage("Warning - Entry will be deleted")
    setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
      Toast.makeText(requireContext(),"${voc.foreignWord} deleted",Toast.LENGTH_SHORT).show()
      mainViewModel.delete(voc)
    })
    setNegativeButton("Abort", DialogInterface.OnClickListener { dialog, id ->
      dialog.dismiss()
    })
  }
  val dialog = builder.create()
  dialog.show()
}
```
To create a simple AlertDialog, first we have to create an AlertDialogBuilder and pass a context, which we will get with **requireContext()**. Then we modify the builder, so we can set a title and the positiv and negativ button. If the user clicks the positiv button, we will delete the clicked item from the database. Otherwise, we just dismiss the AlertDialog with **dialog.dismiss()**. At the end we create the dialog with **builder.create()** and call **dialog.show()**, so the user see the dialog.

### 3.3) Implement the Interface in **MainFragment.kt** and call the AlarmDialog to confirm the delete action
Similar to **2.2)** we implement the OnItemLongClick Interface within the **initRecyclerView()** method in MainFragment.kt:
```
adapter.setOnItemLongClickListener(object:VocListAdapter.OnItemLongClickListener{
  override fun setOnItemLongClickListener(pos: Int) {
    startAlarmDialog(adapter.content[pos])
  }
})
```
Now you can start the emulator and test the new functions. Have Fun!
