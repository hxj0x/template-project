// import { IFormItemProps } from "@/component/form-editer/components";
// import { create } from "zustand";

// export type IFormEditorComponent = {
//     fe_id: string; // 前端生成的 id ，服务端 Mongodb 不认这种格式，所以自定义一个 fe_id
//     type: string;
//     title: string;
//     defaultProps: IFormItemProps;
//     overwriteProps?: IFormItemProps;
// };

// interface IFormEditorStore {
//     componentList: IFormEditorComponent[];
//     setComponentList: (componentList: IFormEditorComponent[]) => void;
// }

// export const useFormEditorStore = create<IFormEditorStore>((set) => ({
//     componentList: [],
//     setComponentList: (componentList: IFormEditorComponent[]) => {
//         set({ componentList });
//     },
// }));
