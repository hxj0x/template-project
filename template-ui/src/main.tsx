import { StyleProvider } from "@ant-design/cssinjs";
import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import "./index.css";
import router from "./router/routes.tsx";
// import "./wdyr";

ReactDOM.createRoot(document.getElementById("root")!).render(
    // <React.StrictMode>
    <StyleProvider hashPriority="high">
        <RouterProvider router={router} />
    </StyleProvider>,
    // </React.StrictMode>,
);
