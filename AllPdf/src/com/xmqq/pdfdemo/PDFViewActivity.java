package com.xmqq.pdfdemo;

import java.io.File;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import static java.lang.String.format;

public class PDFViewActivity extends Activity implements OnPageChangeListener {
    public static final String SAMPLE_FILE = "sample.pdf";
    public static final String ABOUT_FILE = "about.pdf";
    
    String url = "http://www.simuyun.com/peyunupload//2015-07-13/40058fcd26d9431cbdc8b9afcf83f3e8.pdf";
    PDFView pdfView;
    String pdfName = SAMPLE_FILE;
    Integer pageNumber = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	
    	pdfView = (PDFView) this.findViewById(R.id.pdfView);
    	
    	Log.d("AAA", "SAMPLE_FILE = " + SAMPLE_FILE);
    	display(SAMPLE_FILE, true);
    }

    private void display(String assetFileName, boolean jumpToFirstPage) {
        if (jumpToFirstPage) pageNumber = 1;

        Log.d("AAA", "assetFileName = " + assetFileName);
        pdfView.fromAsset(assetFileName)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .load();
        
        
//        pdfView.fromFile(new File(assetFileName))
//        .defaultPage(pageNumber)
//        .onPageChange(this)
//        .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        Toast.makeText(PDFViewActivity.this, page + "/" + pageCount, Toast.LENGTH_SHORT).show();
        //setTitle(format("%s %s / %s", pdfName, page, pageCount));
    }

    @Override
    public void onBackPressed() {
        if (ABOUT_FILE.equals(pdfName)) {
            display(SAMPLE_FILE, true);
        } else {
            super.onBackPressed();
        }
    }

}
