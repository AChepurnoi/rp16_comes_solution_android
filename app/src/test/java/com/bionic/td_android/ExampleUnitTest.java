package com.bionic.td_android;

import android.content.res.AssetManager;

import com.bionic.td_android.MainWindow.SugarApp;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void openFile() throws Exception {
        AssetManager am = SugarApp.getAppContext().getAssets();
        InputStream is = am.open("test.txt");
        System.out.println(is);
    }
}