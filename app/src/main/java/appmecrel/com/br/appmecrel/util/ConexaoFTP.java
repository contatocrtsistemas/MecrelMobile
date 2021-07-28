package appmecrel.com.br.appmecrel.util;

/**
 * Created by Lucas Rodrigues. on 13/05/2015.
 */

import android.os.Environment;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ConexaoFTP {

    FTPClient mFtp;
    String TAG = "classeFTP";

    public FTPFile[] listarDiretorios(String Diretorio) throws Exception {

        FTPFile[] ftpFiles = mFtp.listFiles(Diretorio);
        return ftpFiles;

    }

    public boolean mudarDiretorio(String Diretorio) {
        try {
            mFtp.changeWorkingDirectory(Diretorio);
        } catch (Exception e) {
			  Log.e(TAG, "Erro: não foi possível mudar o diretório para " +
                      Diretorio);
        }
        return false;
    }

    public boolean desconectar() {
        try {
            mFtp.disconnect();
            mFtp = null;
            return true;
        } catch (Exception e) {
             Log.e(TAG, "Erro: ao desconectar. " + e.getMessage());
        }
        return false;
    }

    public boolean conectar(String Host, String Usuario, String Senha, int Porta) {
        try {
            mFtp = new FTPClient();
            mFtp.connect(Host, Porta);
            if (FTPReply.isPositiveCompletion(mFtp.getReplyCode())) {
                boolean status = mFtp.login(Usuario, Senha);
                mFtp.setFileType(FTP.BINARY_FILE_TYPE);
                mFtp.enterLocalPassiveMode();
                return status;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return false;
    }

    public boolean downloadArquivo(String DiretorioOrigem, String ArqOrigem,
                                String ArqDestino) {
        boolean status = false;
        try {
            mudarDiretorio(DiretorioOrigem);
            FileOutputStream desFileStream = new FileOutputStream(ArqDestino);
            mFtp.setFileType(FTP.BINARY_FILE_TYPE);
            mFtp.enterLocalActiveMode();
            mFtp.enterLocalPassiveMode();
            status = mFtp.retrieveFile(ArqOrigem, desFileStream);
            desFileStream.close();
            desconectar();
            return status;
        } catch (Exception e) {
            Log.e(TAG, "Erro: Falha ao efetuar download. " + e.getMessage());
        }
        return status;
    }

    public boolean uploadArquivo(String diretorio, String nomeArquivo) throws Exception{
        boolean status = false;

        FileInputStream arqEnviar = new FileInputStream(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + diretorio);
        mFtp.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);
        mFtp.setFileType(FTPClient.STREAM_TRANSFER_MODE);
        status = mFtp.storeFile(nomeArquivo, arqEnviar);
        mFtp.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);
        mFtp.setFileType(FTPClient.STREAM_TRANSFER_MODE);
       // desconectar();

        return status;
    }
}
