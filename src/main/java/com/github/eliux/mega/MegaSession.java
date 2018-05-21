package com.github.eliux.mega;

import com.github.eliux.mega.auth.MegaAuth;
import com.github.eliux.mega.cmd.*;
import com.github.eliux.mega.cmd.*;

import java.util.function.Predicate;

public class MegaSession {

    private MegaAuth authentication;

    public MegaSession(MegaAuth authentication) {
        this.authentication = authentication;
    }

    public MegaAuth getAuthentication() {
        return authentication;
    }

    public void changePassword(String oldPassword, String newPassword) {
        new MegaCmdChangePassword(oldPassword, newPassword).run();
        System.setProperty(Mega.PASSWORD_ENV_VAR, newPassword);
    }

    public void logout() {
        new MegaCmdLogout().run();
    }

    public String sessionID() {
        return new MegaCmdSession().call();
    }

    public String whoAmI() {
        return new MegaCmdWhoAmI().call();
    }

    public MegaCmdPutSingle uploadFile(String localFilePath) {
        return new MegaCmdPutSingle(localFilePath);
    }

    public MegaCmdPutSingle uploadFile(String localFilePath, String remotePath) {
        return new MegaCmdPutSingle(localFilePath, remotePath);
    }

    public MegaCmdPutMultiple uploadFiles(String remotePath, String... filenames) {
        return new MegaCmdPutMultiple(remotePath, filenames);
    }

    public MegaCmdMakeDirectory makeDirectory(String remotePath) {
        return new MegaCmdMakeDirectory(remotePath);
    }

    public MegaCmdCopy copy(String remoteSourcePath, String remoteTarget) {
        return new MegaCmdCopy(remoteSourcePath, remoteTarget);
    }

    public MegaCmdMove move(String remoteSourcePath, String remoteTarget) {
        return new MegaCmdMove(remoteSourcePath, remoteTarget);
    }

    public MegaCmdList ls(String remotePath) {
        return new MegaCmdList(remotePath);
    }

    public MegaCmdGet get(String remotePath) {
        return new MegaCmdGet(remotePath);
    }

    public MegaCmdGet get(String remotePath, String localPath) {
        return new MegaCmdGet(remotePath, localPath);
    }

    public MegaCmdRemove remove(String remotePath) {
        return new MegaCmdRemove(remotePath);
    }

    public MegaCmdRemove removeDirectory(String remotePath) {
        return new MegaCmdRemove(remotePath).deleteRecursively();
    }

    public long count(String remotePath) {
        return ls(remotePath).count();
    }

    public long count(String remotePath, Predicate<FileInfo> predicate) {
        return ls(remotePath).count(predicate);
    }

    public boolean exists(String remotePath) {
        return ls(remotePath).exists();
    }

    public MegaCmdShare share(String remotePath, String userMailToShareWith) {
        return new MegaCmdShare(remotePath, userMailToShareWith);
    }

    public MegaCmdExport export (String remotePath){
        return new MegaCmdExport(remotePath);
    }
}