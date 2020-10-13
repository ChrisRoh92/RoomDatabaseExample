# RoomDatabaseExample - Part 3

## Tasks
1) Create a Repository

2) Create the MainViewModel.kt

3) Create the MainViewModelFactory

4) Implement the Methods to interact with Room @ MainViewModel.kt
## Solution
### 1) Create a simple Repository **AppRepository.kt**
<details>
<summary> Click to See the Code for the AppRepository.kt</summary>

```
class AppRepository(application: Application)
{
    private val vocDao:VocDao

    init {
        val db = VocDataBase.createInstance(application)
        vocDao = db.vocDao
    }

    // Implement all functions
    suspend fun insert(voc: Voc)
    {
        withContext(Dispatchers.IO)
        {
            vocDao.insert(voc)
        }
    }

    suspend fun delete(voc:Voc)
    {
        withContext(Dispatchers.IO)
        {
            vocDao.delete(voc)
        }
    }

    suspend fun update(voc:Voc)
    {
        withContext(Dispatchers.IO)
        {
            vocDao.update(voc)
        }
    }

    suspend fun getVocById(vocId:Long):Voc?
    {
        var voc:Voc? = null
        withContext(Dispatchers.IO)
        {
            voc =  vocDao.getVocById(vocId)
        }
        return voc
    }

    suspend fun getAllVocs():List<Voc>?
    {
        var vocs:List<Voc>? = null
        withContext(Dispatchers.IO)
        {
            vocs =  vocDao.getVocList()
        }
        return vocs
    }

    fun getLiveDataVocs():LiveData<List<Voc>>
    {
        return vocDao.getLiveDataVocList()
    }
}
```
</details>

### 2) Create the ViewModel **MainViewModel.kt**
```
class MainViewModel(application: Application): AndroidViewModel(application)
{
    ////////////////////////////////////////////////////////////
    // Repository:
    private val repository = AppRepository(application)
    private var liveVocList = repository.getLiveDataVocs()
}
```

### 3) Create the ViewModelFactory **MainViewModelFactory.kt**
```
class MainViewModelFactory(application: Application):
    ViewModelProvider.AndroidViewModelFactory(application) {
}
```

### 4) Implement the Methods to interact with Room @ **MainViewModel.kt**
<details>
<summary> Click to See the Methods to interact with the repository</summary>
  
```
// Methods to interact with the repository:
fun insert(nativeWord:String,foreignWord:String)
{
  viewModelScope.launch {
    val voc = Voc(0L,nativeWord,foreignWord,Date().toStringFormat(),0)
    repository.insert(voc)
  }
}

fun update(voc:Voc)
{
  viewModelScope.launch {
    repository.update(voc)
  }
}

fun delete(voc:Voc)
{
  viewModelScope.launch {
    repository.delete(voc)
  }
}

fun getVocById(vocId:Long):Voc?
{
  var voc:Voc? = null
  viewModelScope.launch {
    voc = repository.getVocById(vocId)
  }
  return voc
}

fun getAllVocs():List<Voc>?
{
  var vocs:List<Voc>? = null
  viewModelScope.launch {
    vocs =  repository.getAllVocs()
  }
  return vocs
}
```
</details>


Method to get the LiveData for the Observer in the Fragment etc.
```
    
// Getters for LiveData
fun getLiveVocList():LiveData<List<Voc>> = liveVocList
```

Util Methods to format Date() to String
```
// Utils
private fun Date.toStringFormat(pattern:String="dd.MM.yyyy"):String
{
  return SimpleDateFormat(pattern).format(this)
}
```
