package br.com.ecommerce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class GsonDeserialize<T> implements Deserializer {

    public static final String TYPE_CONFIG = "br.com.alura.ecommerce.type_config";
    private final Gson gson = new GsonBuilder().create();
    private Class<T> type;

    @Override
    public void configure(Map configs, boolean isKey) {
        String typename = String.valueOf(configs.get(TYPE_CONFIG));
        try {
        this.type = (Class<T>) Class.forName(typename);

        }catch (ClassNotFoundException e){
            throw new RuntimeException("Classe nao exite"+ e);
        }
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        return gson.fromJson(new String(data), type);
    }
}
