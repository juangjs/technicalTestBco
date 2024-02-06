package co.com.devex.utils;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import co.com.devex.model.utils.IUtilMethods;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UtilMethods implements IUtilMethods {
	private final Gson gson;

	@Override
	public String toMapperStrignObject(Object object) {
		return gson.toJson(object);
	}
}
