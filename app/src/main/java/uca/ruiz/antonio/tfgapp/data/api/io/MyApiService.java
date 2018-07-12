package uca.ruiz.antonio.tfgapp.data.api.io;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import uca.ruiz.antonio.tfgapp.data.api.mapping.Login;
import uca.ruiz.antonio.tfgapp.data.api.mapping.TokenResponse;
import uca.ruiz.antonio.tfgapp.data.api.mapping.User;
import uca.ruiz.antonio.tfgapp.data.api.mapping.UserResponse;
import uca.ruiz.antonio.tfgapp.data.api.model.Centro;
import uca.ruiz.antonio.tfgapp.data.api.model.Cura;
import uca.ruiz.antonio.tfgapp.data.api.model.Paciente;
import uca.ruiz.antonio.tfgapp.data.api.model.Proceso;
import uca.ruiz.antonio.tfgapp.data.api.model.Sala;


public interface MyApiService {


    /* ======================= AUTENTICACION Y REGISTRO CON JWT ======================= */
    @Headers("Content-Type: application/json")
    @POST("auth")
    Call<TokenResponse> login(@Body Login login);

    @Headers("Content-Type: application/json")
    @POST("registro")
    Call<UserResponse> registro(@Body User user, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @GET("user")
    Call<UserResponse> getUser(@Header("Authorization") String token);


    /* ======================= PACIENTES ======================= */
    @Headers("Content-Type: application/json")
    @GET("api/pacientes")
    Call<ArrayList<Paciente>> getPacientes(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @GET("api/pacientes/{id}")
    Call<Paciente> getPaciente(@Path("id") Long id, @Header("Authorization") String token);


    /* ======================= PROCESOS ======================= */
    @Headers("Content-Type: application/json")
    @GET("api/procesos")
    Call<ArrayList<Proceso>> getProcesos(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @GET("api/procesos/{id}")
    Call<Proceso> getProceso(@Path("id") Long id, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("api/procesos")
    Call<Proceso> crearProceso(@Body Proceso p, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @PUT("api/procesos/{id}")
    Call<Proceso> editarProceso(@Path("id") Long id, @Body Proceso p, @Header("Authorization") String token);


    /* ======================= CURAS ======================= */
    @Headers("Content-Type: application/json")
    @GET("api/procesos/{id}/curas")
    Call<ArrayList<Cura>> getCurasByProcesoId(@Path("id") Long id, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("api/curas")
    Call<Cura> crearCura(@Body Cura c, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @PUT("api/curas/{id}")
    Call<Cura> editarCura(@Path("id") Long id, @Body Cura c, @Header("Authorization") String token);


    /* ======================= CENTROS ======================= */
    @Headers("Content-Type: application/json")
    @GET("api/centros")
    Call<ArrayList<Centro>> getCentros(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @GET("api/centros/filtro")
    Call<ArrayList<Centro>> getCentrosByFiltro(@Query("filtro") String filtro,
                                           @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @GET("api/centros/{id}")
    Call<Centro> getCentro(@Path("id") Long id, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @DELETE("api/centros/{id}")
    Call<String> borrarCentro(@Path("id") Long id, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("api/centros")
    Call<Centro> crearCentro(@Body Centro c, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @PUT("api/centros/{id}")
    Call<Centro> editarCentro(@Path("id") Long id, @Body Centro c, @Header("Authorization") String token);


    /* ======================= SALAS ======================= */
    @Headers("Content-Type: application/json")
    @GET("api/salas")
    Call<ArrayList<Sala>> getSalas(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @GET("api/salas/filtro")
    Call<ArrayList<Sala>> getSalasByFiltro(@Query("filtro") String filtro,
                                           @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @GET("api/salas/{id}")
    Call<Sala> getSala(@Path("id") Long id, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @DELETE("api/salas/{id}")
    Call<String> borrarSala(@Path("id") Long id, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("api/salas")
    Call<Sala> crearSala(@Body Sala s, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @PUT("api/salas/{id}")
    Call<Sala> editarSala(@Path("id") Long id, @Body Sala s, @Header("Authorization") String token);

}
