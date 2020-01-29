package dz.apps.constraintsetexample;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

String TAG = "zak";

//Bolean for testing if views are permuted
Boolean isPermuted=false;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);


	ConstraintLayout cl = findViewById(R.id.cl);
	ImageButton strawberry = findViewById(R.id.strawberry);
	ImageButton banana = findViewById(R.id.banana);
	ImageButton permute = findViewById(R.id.permute);


	//add manually id of all views to idViews ArrayList, We use it later !
	//add id of ConstraintLayout cl & all ImageButton

	int[] idViews = new int[]{strawberry.getId(), permute.getId(),banana.getId() };
	int[] idViewsPermuted = new int[]{banana.getId(), permute.getId(), strawberry.getId()};

	permute.setOnClickListener((View v) -> {

		ConstraintSet set = new ConstraintSet();
		set.clone(cl);

		//Remove START & AND Constraint of strawberry View
		set.clear(R.id.strawberry, ConstraintSet.START);
		set.clear(R.id.strawberry, ConstraintSet.END);

		//Remove START & AND Constraint of banana View
		set.clear(R.id.banana, ConstraintSet.START);
		set.clear(R.id.banana, ConstraintSet.END);

		if(isPermuted){

			//Connect START & END Constraint of banana View
			//params of connect method => connect(int startID, int startSide, int endID,  int endSide);

			//Connect START Constraint of banana View to START of cl ConstraintLayout
			set.connect(R.id.banana, ConstraintSet.START, R.id.permute, ConstraintSet.END);

			//Connect END Constraint of banana View to START of permute  Button
			set.connect(R.id.banana, ConstraintSet.END, R.id.cl, ConstraintSet.END);

			//Connect START Constraint of strawberry View to END of permute  View
			set.connect(R.id.strawberry, ConstraintSet.START, R.id.cl, ConstraintSet.START);

			//Connect END Constraint of strawberry View to END of cl ConstraintLayout
			set.connect(R.id.strawberry, ConstraintSet.END, R.id.permute, ConstraintSet.START);

			//Connect START Constraint of permute View to END of banana  View
			set.connect(R.id.permute, ConstraintSet.START, R.id.strawberry, ConstraintSet.END);

			//Connect END Constraint of permute View to START of strawberry
			set.connect(R.id.permute, ConstraintSet.END, R.id.banana, ConstraintSet.START);

			//set layout_constraintHorizontal_chainStyle="spread"
			set.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, idViews, null, ConstraintSet.CHAIN_SPREAD);

			//Apply new Constraint to CnstraintLayout
			set.applyTo(cl);

			isPermuted=false;
			Toast.makeText(this,"View PERMUTED => layout_constraintHorizontal_chainStyle=\"spread\"",Toast.LENGTH_LONG).show();

		}else {


			//Connect START & END Constraint of banana View
			//params of connect method => connect(int startID, int startSide, int endID,  int endSide);

			//Connect START Constraint of banana View to START of cl ConstraintLayout
			set.connect(R.id.banana, ConstraintSet.START, R.id.cl, ConstraintSet.START);

			//Connect END Constraint of banana View to START of permute  Button
			set.connect(R.id.banana, ConstraintSet.END, R.id.permute, ConstraintSet.START);

			//Connect START Constraint of strawberry View to END of permute  View
			set.connect(R.id.strawberry, ConstraintSet.START, R.id.permute, ConstraintSet.END);

			//Connect END Constraint of strawberry View to END of cl ConstraintLayout
			set.connect(R.id.strawberry, ConstraintSet.END, R.id.cl, ConstraintSet.END);

			//Connect START Constraint of permute View to END of banana  View
			set.connect(R.id.permute, ConstraintSet.START, R.id.banana, ConstraintSet.END);

			//Connect END Constraint of permute View to START of strawberry
			set.connect(R.id.permute, ConstraintSet.END, R.id.strawberry, ConstraintSet.START);

			//set layout_constraintHorizontal_chainStyle="spread_inside"
			set.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, idViewsPermuted, null, ConstraintSet.CHAIN_SPREAD_INSIDE);

			//Apply new Constraint to CnstraintLayout
			set.applyTo(cl);

			isPermuted=true;
			Toast.makeText(this,"View PERMUTED => layout_constraintHorizontal_chainStyle=\"spread_inside\"",Toast.LENGTH_LONG).show();

		}
	});


}
}
