import App from "@/App";
import DndTable from "@/pages/DndTable";
import { createBrowserRouter } from "react-router-dom";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />,
    },
    {
        path: "/DndTable",
        element: <DndTable />,
    },
    // {
    //     path: "/",
    //     element: <AdminLayout />,
    //     children: [
    //         {
    //             path: "/build/table/:name",
    //             element: <EruptTable />,
    //         },
    //         {
    //             path: "/build/tree/:name",
    //             element: <EruptTree />,
    //         },
    //         {
    //             path: "/tpl/:name",
    //             element: <EruptTpl />,
    //         },
    //         {
    //             path: "/form-editer",
    //             element: <FormEditor />,
    //         },
    //         {
    //             path: "/form-editer-title",
    //             element: <TitleDemo />,
    //         },
    //         {
    //             path: "/form-editer-input",
    //             element: <FormInputDemo />,
    //         },
    //     ],
    // },
    // {
    //     path: "/Login",
    //     element: <Login />,
    // },
    // {
    //     path: "/bpmn",
    //     element: <BpmnEditer />,
    // },
    // {
    //     path: "/form-builder",
    //     element: <FormBuilder />,
    // },
]);

export default router;
