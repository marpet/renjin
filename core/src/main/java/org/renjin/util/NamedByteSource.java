package org.renjin.util;

import com.google.bc.common.io.ByteSource;

import java.io.IOException;
import java.io.InputStream;

public class NamedByteSource extends ByteSource {

  private String name;
  private ByteSource origin;

  public NamedByteSource(String name, ByteSource origin) {
    this.name = name;
    this.origin = origin;
  }

  public String getName()
  {
    return name;
  }

  public InputStream openStream() throws IOException { 
    return origin.openStream(); 
  }
}
