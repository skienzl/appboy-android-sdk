package com.appboy.sample;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.appboy.IAppboyEndpointProvider;

/**
 * An {@link IAppboyEndpointProvider} that sets an override endpoint if given.
 */
public class DroidboyEndpointProvider implements IAppboyEndpointProvider {
  private Uri mEndpointUri;
  private String mEndpoint;

  public DroidboyEndpointProvider(@NonNull String endpoint) {
    mEndpoint = endpoint;
    mEndpointUri = Uri.parse(endpoint);
  }

  public Uri getApiEndpoint(Uri appboyEndpoint) {
    final Uri.Builder builder = appboyEndpoint.buildUpon();

    if (mEndpointUri.getScheme().equals("http")) {
      builder.scheme("http");
    }

    if (mEndpointUri.getEncodedAuthority() != null) {
      builder.encodedAuthority(mEndpointUri.getEncodedAuthority());
    } else {
      builder.encodedAuthority(mEndpoint);
    }
    return builder.build();
  }
}
