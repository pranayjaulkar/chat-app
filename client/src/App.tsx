import { Toaster } from "react-hot-toast";
import { createBrowserRouter, Outlet } from "react-router";
import { RouterProvider } from "react-router/dom";
import Home from "./pages/Home";
import Auth from "./pages/Auth";
import AppProvider from "./components/AppProvider";

function RootLayout() {
  return (
    <AppProvider>
      <Toaster />
      <Outlet />
    </AppProvider>
  );
}

const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    children: [
      { index: true, element: <Home /> },
      {
        path: "/auth",
        element: <Auth />,
      },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
