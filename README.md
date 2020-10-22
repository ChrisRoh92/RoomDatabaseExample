# RoomDatabaseExample
Hello and welcome to this **Android Room Tutorial** from **CodingWithChris**. In this series, i want you to show, how simple you can create an app, which uses the **Android Room library** to store data.

I created for each part of this series a branch, so you do not have to start from the very beginning. In my Videos, i will explain, how you can use this repository. In the follwing, you get the Tasks, which are already implemented in the different branches:
___
## Branch: Init_Branch
### Initial File of the Video Series :
This will be the start of the whole video series. In this branch, i just create a simple project in Android Studio.

## Branch: Part_1
### Changes on Master after Part 1 Video
The following Tasks are implemented in this branch:
1) Add RecyclerView to fragment_main.xml
2) Create item_voc.xml as childlayout for the RecyclerView
3) Create the VocListAdapter (RecyclerView.Adapter) for all available Vocabularies
4) Implement the RecyclerView in MainFragment.kt
5) Create a new Drawable for the FloatingActionButton

#### How the Layout of the MainFragment should look like
<img src="https://github.com/ChrisRoh92/RoomDatabaseExample/blob/Part_1/screenshot/MainFragment_Preview.png?raw=true" width="250">

## Branch: Part_2
### Changes on Part_1 after Part 2 Video
1) Add the Gradle Dependencies

2) Create the Voc Entity Class

3) Create the Data Access Object (DAO)

4) Create the VocDataBase Class

## Branch: Part_3
### Changes on Part_2 after Part 3 Video
1) Create a Repository

2) Create the MainViewModel.kt

3) Create the MainViewModelFactory

4) Implement the Methods to interact with Room @ **MainViewModel.kt**

## Branch: Part_4
### Changes on Part_3 after Part 4 Video
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

## Branch: Part_5
### Changes on Part_4 after Part 5 Video
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
