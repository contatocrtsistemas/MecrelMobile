package appmecrel.com.br.appmecrel.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.model.bean.Configuracoes;


public class ConfiguracoesActivity extends FragmentActivity {

    FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(
                tabHost.newTabSpec("Configurações").setIndicator("Configurações", null),
                ConfigConfiguracoesActivity.class, null);
        tabHost.addTab(
                tabHost.newTabSpec("Conexão").setIndicator("Conexão", null),
                ConfigConexaoActivity.class, null);
        tabHost.addTab(
                tabHost.newTabSpec("Sequencial").setIndicator("Sequencial", null),
                ConfigSeguencialActivity.class, null);

        tabHost.addTab(
                tabHost.newTabSpec("BackUp").setIndicator("BackUp", null),
                ConfigBackupActivity.class, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configuracoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
