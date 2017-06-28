package comp3350.plantr.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comp3350.plantr.R;

/**
 * To be removed if we decide not to have this showing until next iteration
 * <p>
 * Compost Bin View Fragment
 */

public class CompostBinFragment extends Fragment {
	View myView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.compost_bin_layout, container, false);
		return myView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getActivity().setTitle(getString(R.string.compostbinview_title));
	}
}
