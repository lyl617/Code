class MyHashtable implements DictionaryInterface {
    class Entry{
    	private String key;
    	private Object value;
    	public Entry(String key,Object value){
    	    this.key=key;
    	    this.value=value;
        }
	}
	protected int tableSize;// the size of the array being used by the hashtable
    protected int size;//the number of key/value entries stored in the hashtable
    protected MyLinkedList[] table;//an array of MyLinkedList

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object put(String key, Object value) {
        int hashcode=key.hashCode();
        int arrayIndex=Math.abs(hashcode)%tableSize;//get index of key
        if (table[arrayIndex]==null){//If that location in the table is  null
            MyLinkedList new_list=new MyLinkedList();
            new_list.add(0,new Entry(key,value));
            table[arrayIndex]=new_list;
            this.size++;
        }else{//If the location in the table isn't  null ,
            MyLinkedList temp_linklist=table[arrayIndex];//get element which index is arrayIndex
            for(int i=0;i<temp_linklist.size;i++)//Traverse the list and compare the key for each entry with the key passed into  get()
            {
                Entry temp_entry=(Entry)temp_linklist.get(i);//get entry at index i
                if(temp_entry.key.equals(key))//if key exists
                {
                    temp_entry.value=value;//change the value
                    return temp_entry;
                }

            }
            temp_linklist.add(temp_linklist.size,new Entry(key,value));//if key does not exist,add key/value to list
            this.size++;
        }
        return null;
    }

    @Override
    public Object get(String key) {
        int hashcode=key.hashCode();
        int arrayIndex=Math.abs(hashcode)%tableSize;//get index of key
        if(table[arrayIndex]!=null)
        {
           MyLinkedList temp_linklist=table[arrayIndex];//get element which index is arrayIndex
           for(int i=0;i<temp_linklist.size;i++)//Traverse the list and compare the key for each entry with the key passed into  get()
            {
               Entry temp_entry=(Entry)temp_linklist.get(i);//get entry at index i
                if(temp_entry.key.equals(key))
                {
                    return temp_entry.value;
                }

           }
        }
        return null;
    }

    @Override
    public void remove(String key) {
        int hashcode=key.hashCode();
        int arrayIndex=Math.abs(hashcode)%tableSize;//get index of key
        if (table[arrayIndex]!=null){
            MyLinkedList temp_linklist=table[arrayIndex];//get element which index is arrayIndex
            for(int i=0;i<temp_linklist.size;i++)//Traverse the list and compare the key for each entry with the key passed into  get()
            {
                Entry temp_entry=(Entry)temp_linklist.get(i);//get entry at index i
                if(temp_entry.key.equals(key))
                {
                    temp_linklist.remove(i);
                    this.size--;
                }

            }
        }
    }

    @Override
    public void clear() {
       this.size=0;
       this.table=new MyLinkedList[this.tableSize];
    }

    @Override
    public String[] getKeys() {
        String[] keys=new String[this.size];//array to store keys
        int key_index=0;
        for(MyLinkedList ll :table){
            if (ll!=null){
                for(int i=0;i<ll.size;i++){
                    Entry temp_entry=(Entry)ll.get(i);
                    keys[key_index]=temp_entry.key;
                    key_index++;
                }
            }
        }
        return keys;
    }
    public MyHashtable(int tableSize){
        this.tableSize=tableSize;
        this.size=0;
        table=new MyLinkedList[tableSize];
    }

    // Returns the size of the biggest bucket (most collisions) in the hashtable.
	public int biggestBucket() {
		int biggestBucket = 0; 
		for(int i = 0; i < table.length; i++) {
			// Loop through the table looking for non-null locations. 
			if (table[i] != null) {
				// If you find a non-null location, compare the bucket size against the largest
				// bucket size found so far. If the current bucket size is bigger, set biggestBucket
				// to this new size. 
				MyLinkedList bucket = table[i];
				if (biggestBucket < bucket.size())
					biggestBucket = bucket.size();
			}
		}
		return biggestBucket; // Return the size of the biggest bucket found. 
	}

	// Returns the average bucket length. Gives a sense of how many collisions are happening overall.
	public float averageBucket() {
		float bucketCount = 0; // Number of buckets (non-null table locations)
		float bucketSizeSum = 0; // Sum of the size of all buckets
		for(int i = 0; i < table.length; i++) {
			// Loop through the table 
			if (table[i] != null) {
				// For a non-null location, increment the bucketCount and add to the bucketSizeSum
				MyLinkedList bucket = table[i];
				bucketSizeSum += bucket.size();
				bucketCount++;
			}
		}

		// Divide bucketSizeSum by the number of buckets to get an average bucket length. 
		return bucketSizeSum/bucketCount; 
	}

	public String toString() {
		String s = "";
		for(int tableIndex = 0; tableIndex < tableSize; tableIndex++) {
			if (table[tableIndex] != null) {
				MyLinkedList bucket = table[tableIndex];
				for(int listIndex = 0; listIndex < bucket.size(); listIndex++) {
					Entry e = (Entry)bucket.get(listIndex);
					s = s + "key: " + e.key + ", value: " + e.value + "\n";
				}
			}
		}
		return s; 
	}
}