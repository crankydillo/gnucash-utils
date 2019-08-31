package org.beeherd.ofx;

import java.io.*;

import com.webcohesion.ofx4j.domain.data.ResponseEnvelope;
import com.webcohesion.ofx4j.io.AggregateMarshaller;
import com.webcohesion.ofx4j.io.AggregateUnmarshaller;
import com.webcohesion.ofx4j.io.v1.OFXV1Writer;

public class Cli {

  public static void main(final String[] args) throws Exception {
    final File file = new File(args[0]);
    final InputStream in = new FileInputStream(file);
    final OFXV1Writer writer = new OFXV1Writer(System.out);

    try {
      final AggregateUnmarshaller<ResponseEnvelope> unmarshaller =
              new AggregateUnmarshaller<>(ResponseEnvelope.class);
      final Object data = unmarshaller.unmarshal(in);
      new AggregateMarshaller().marshal(data, writer);
      writer.flush();
    } finally {
      try { in.close(); } catch (Exception e) { e.printStackTrace(); }
      try { writer.close(); } catch (Exception e) { e.printStackTrace(); }
    }

  }

}
