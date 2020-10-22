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
  
    3.2) Create a simple AlarmDialog to confirm the delete action
  
    3.3) Call the **fun delete(voc:Voc)** method to remove the entry
  
    
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
