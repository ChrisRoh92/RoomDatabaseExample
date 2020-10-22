# RoomDatabaseExample - Part 5
## Instructions
If you want to do this Part of the Room Database Tutorial, beginn with the Branch **Part_4** and do the tasks, given in the section below.

# Tasks

1) Set clickable Background for the Item Layout **item_voc.xml**

2) Create an OnClickListener for each Item of the RecyclerView - show an entry
  
    2.1) Create an Interface OnItemClickListener
  
    2.2) Implement the Interface in **MainFragment.kt**
  
    2.3) Update and call the **InputDialog.kt** if user want to change the data of the entry
  
    2.4) Create an OnConflictStrategy for **fun insert(voc:Voc)** in the **VocDao.kt**

3) Create an OnLongClickListener for each item of the RecyclerView - Delete an entry

    3.1) Create an Interface OnItemLongClickListener
  
    3.2) Create a simple AlarmDialog to confirm the delete action
  
    3.3) Call the **fun delete(voc:Voc)** method to remove the entry
  
    
## Solution
