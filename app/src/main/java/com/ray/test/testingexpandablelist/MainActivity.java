package com.ray.test.testingexpandablelist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private List<Map<String, Object>> groupData;
    private List<List<Map<String, Object>>> childData;
    private ExpandableListView elst;

    private String[] arr_child01 = new String[]{"Milk", "Apple", "Banana", "Orange"}
            , arr_child02 = new String[]{"Tom","Jack","Ray","Mark","Jason","Coco"}
            , arr_child03_1 = new String[]{"Child01","Child02","Child03","Child04","Child05","Child06","Child07","Child08"}
            , arr_child03_2 = new String[]{"Demo01","Demo02","Demo03","Demo04","Demo05","Demo06","Demo07","Demo08"}
            , arr_group = new String[]{ "Food", "People", "Demo Group" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elst = (ExpandableListView) findViewById(R.id.elst);
        prepareData();
        SimpleExpandableListAdapter adapter =
                new SimpleExpandableListAdapter(this,
                        groupData, R.layout.group_item,
                        new String[]{"Group"}, new int[]{R.id.txt_group},
                        childData, R.layout.child_item,
                        new String[]{"Child","Demo"}, new int[]{R.id.txt_child, R.id.txt_demo});

        // change group indicator style
        elst.setGroupIndicator(null);
// elst.setGroupIndicator(getResources().getDrawable( R.drawable.ic_launcher));

        elst.setAdapter(adapter);
        for(int i=0;i<arr_group.length;i++){
            // open all group up
            elst.expandGroup(i);
        }

        elst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position_id, long id) {
                Toast.makeText(MainActivity.this,
                        position_id+"", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void prepareData() {
        groupData = new ArrayList<Map<String, Object>>();
        childData = new ArrayList<List<Map<String, Object>>>();

        for (int i = 0; i < arr_group.length; i++) {
            Map<String, Object> groupObj = new HashMap<String, Object>();
            groupObj.put("Group", arr_group[i]);
            groupData.add(groupObj);

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            switch (i) {
                case 0:
                    for (int j = 0; j < arr_child01.length; j++) {
                        Map<String, Object> obj = new HashMap<String, Object>();
                        obj.put("Child", arr_child01[j]);
                        obj.put("Demo", "");
                        list.add(obj);
                    }
                    childData.add(list);
                    break;
                case 1:
                    for (int j = 0; j < arr_child02.length; j++) {
                        Map<String, Object> obj = new HashMap<String, Object>();
                        obj.put("Child", arr_child02[j]);
                        obj.put("Demo", "");
                        list.add(obj);
                    }
                    childData.add(list);
                    break;
                case 2:
                    for (int j = 0; j < arr_child03_1.length; j++) {
                        Map<String, Object> obj = new HashMap<String, Object>();
                        obj.put("Child", arr_child03_1[j]);
                        obj.put("Demo", arr_child03_2[j]);
                        list.add(obj);
                    }
                    childData.add(list);
                    break;
            }
        }
    }
}
