package luis.mvi.uzumbank.presentation.screens.main.chat

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import luis.mvi.uzumbank.R

/*
created by Xo'jaakbar on 15.09.2023 at 2:43
*/

class ChatScreen():Fragment(R.layout.screen_chat) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = Color.WHITE
        setStatusBarItemsColor()
    }

    fun setStatusBarItemsColor() {
        val window = requireActivity().window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
    }
}