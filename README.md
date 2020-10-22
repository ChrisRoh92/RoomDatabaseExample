# RoomDatabaseExample - Part 5
## Instructions
If you want to do this Part of the Room Database Tutorial, beginn with the Branch **Part_4** and do the tasks, given in the section below.

# Tasks

1) Create an OnClickListener for each Item of the RecyclerView - show an entry
  
    1.1) Create an Interface OnItemClickListener
  
    1.2) Implement the Interface in **MainFragment.kt**
  
    1.3) Call the **InputDialog.kt** if user want to change the data of the entry
  
    1.4) Create an OnConflictStrategy for **fun insert(voc:Voc)** in the **VocDao.kt**

2) Create an OnLongClickListener for each item of the RecyclerView - Delete an entry

    2.1) Create an Interface OnItemLongClickListener
  
    2.2) Create a simple AlarmDialog to confirm the delete action
  
    2.3) Call the **fun delete(voc:Voc)** method to remove the entry
  
    
## Solution
