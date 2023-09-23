import axios, { AxiosError, InternalAxiosRequestConfig } from "axios";

const httpClient = axios.create({
    baseURL: "/erupt-api",
    timeout: 60_000,
    withCredentials: false, // disable Cookie, etc
});

httpClient.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
        config.headers["token"] = localStorage.getItem("token");
        return config;
    },
    (error: AxiosError) => {
        // Do something with request error
        console.log(error); // for debug
    },
);

export default httpClient;
