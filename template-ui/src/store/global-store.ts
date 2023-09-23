// import { create } from "zustand";

// interface UserStore {
//     nickname?: string;
//     skipFirstChangeSetting: boolean;
//     setNickName: (nickName: string) => void;
//     setSkipFirstChangeSetting: (firstRender: boolean) => void;
//     clearNickName: () => void;
// }

// export const useUserStore = create<UserStore>((set) => ({
//     nickname: localStorage.getItem("nickName") || undefined,
//     skipFirstChangeSetting: true,
//     setNickName: (nickName) => set({ nickname: nickName }),
//     setSkipFirstChangeSetting: (firstRender) => set({ skipFirstChangeSetting: firstRender }),
//     clearNickName: () => {
//         set({ nickname: undefined });
//     },
// }));
