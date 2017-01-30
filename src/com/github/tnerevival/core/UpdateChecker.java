package com.github.tnerevival.core;

import com.github.tnerevival.core.version.ReleaseType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker {
  private String url;
  private String build;
  private String currentBuild;

  public UpdateChecker(String url, String currentBuild) {
    this.url = url;
    this.currentBuild = currentBuild;
    getLatestBuild();
  }

  public void getLatestBuild() {
    build = currentBuild;
    try {
        URL url = new URL(this.url);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        build = in.readLine();
        in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ReleaseType getRelease() {
    Integer latest = Integer.valueOf(build.replace(".", ""));
    Integer current = Integer.valueOf(currentBuild.replace(".", ""));

    if(latest < current) return ReleaseType.PRERELEASE;
    if(latest.equals(current)) return ReleaseType.LATEST;
    return ReleaseType.OUTDATED;
  }
}