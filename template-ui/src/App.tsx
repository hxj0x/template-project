import { StyleProvider } from "@ant-design/cssinjs";

export default function App() {
    return (
        <>
            <StyleProvider hashPriority="high">
                <div className="h-full w-full">hello app</div>
            </StyleProvider>
        </>
    );
}
